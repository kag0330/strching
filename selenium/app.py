import time
import re
import json
import glob
import requests

from selenium import webdriver
from bs4 import BeautifulSoup
from flask import Flask, jsonify, request
from flask_cors import CORS
from datetime import datetime


app = Flask(__name__)
CORS(app, origins="https://kag0330.duckdns.org")

VIDEO_ID_PATTERN = re.compile(r'v=([\w-]+)')
SCROLL_PAUSE_TIME = 2


def scrape_webpage(driver, max_items, existing_urls):
    valid_videos = set()
    last_height = driver.execute_script("return document.documentElement.scrollHeight")

    while len(valid_videos) < max_items:
        driver.execute_script("window.scrollTo(0, document.documentElement.scrollHeight);")
        time.sleep(SCROLL_PAUSE_TIME)

        new_height = driver.execute_script("return document.documentElement.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height

        soup = BeautifulSoup(driver.page_source, 'html.parser')

        # 각 비디오 항목에 대해 반복
        for video in soup.select('ytd-video-renderer'):
            title_element = video.select_one('div h3 a yt-formatted-string')
            title = title_element.text if title_element else None

            url_element = title_element.find_parent('a') if title_element else None
            url = 'https://www.youtube.com' + url_element['href'] if url_element else None

            if not title or not url or url in existing_urls:
                continue

            uploader_element = video.select_one('div ytd-channel-name div yt-formatted-string a')
            uploader_name = uploader_element.text if uploader_element else "Unknown uploader"

            video_id_match = VIDEO_ID_PATTERN.search(url)
            if video_id_match:
                video_id = video_id_match.group(1)
                img_url = f"https://img.youtube.com/vi/{video_id}/maxresdefault.jpg"
                valid_videos.add((title, url, img_url, uploader_name))

                if len(valid_videos) >= max_items:
                    break

    return valid_videos

#url 읽어오기
def read_all_existing_urls():
    existing_urls = set()
    for json_file in glob.glob('./*.json'):
        with open(json_file, "r", encoding="utf-8") as f:
            data = json.load(f)
            for item in data:
                existing_urls.add(item.get('url', ''))
    return existing_urls

def process_data(scraped_data):
    new_data = []
    seq = 0

    for title, url, img_url, uploader in scraped_data:
        seq += 1
        new_data.append({
            "seq": seq,
            "title": title,
            "url": url,
            "img_url": img_url,
            "uploader": uploader
        })

    return new_data

def save_to_json(data, filename):
    with open(filename, "w", encoding="utf-8") as jsonfile:
        json.dump(data, jsonfile, ensure_ascii=False, indent=4)

@app.route('/')
def index():
    return 'index.html'

@app.route('/cu', methods=['POST'])
def scrape_youtube():
    if request.is_json:
        search_query = request.json.get('search_query')
        max_items = int(request.json.get('max_items', 10))
    else:
        search_query = request.form.get('search_query')
        max_items = int(request.form.get('max_items', 10))

    try:
        all_existing_urls = read_all_existing_urls()

        driver = webdriver.Chrome()
        driver.get(f"https://www.youtube.com/results?search_query={search_query}")

        scraped_data = scrape_webpage(driver, max_items, all_existing_urls)
        processed_data = process_data(scraped_data)

        current_time = datetime.now().strftime("%Y%m%d%H%M%S")
        filename = f"{current_time}_{search_query}.json"
        save_to_json(processed_data, filename)

        try:
            with open(filename, 'rb') as f:
                response = requests.post("https://kag0330.duckdns.org/upload", files={"file": f})
        except Exception as e:
            print(f"An error occurred while sending the file: {e}")

        return jsonify({"data": processed_data, "message": "Scraping complete"}), 200

    except Exception as e:
        return jsonify({"message": f"An error occurred: {str(e)}"}), 500
    finally:
        if 'driver' in locals() and driver:
            driver.quit()

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=8086)
