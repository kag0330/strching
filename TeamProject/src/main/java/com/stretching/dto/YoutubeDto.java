package com.stretching.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stretching.entity.Youtube;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class YoutubeDto {
	
	private String title;
	
	private String url;
	
	@JsonProperty("iframe_url")
	private String iframeUrl;
	
	@JsonProperty("img_url")
	private String imgUrl;
	
	private String uploader;
	
	private String type;
	
	private Date uploadDate;
	
	private Long cnt;
	
	public YoutubeDto() {
		
	}
	
	public YoutubeDto(Youtube entity) {
		this.title = entity.getTitle();
		this.url = entity.getUrl();
		this.iframeUrl = entity.getIframeUrl();
		this.imgUrl = entity.getImgUrl();
		this.uploader = entity.getUploader();
		this.type = entity.getType();
		this.uploadDate = entity.getUploadDate();
		this.cnt = entity.getCnt();
	}
	
	public Youtube toEntity() {
		return Youtube.builder()
				.title(title)
				.url(url)
				.iframeurl(iframeUrl)
				.imgurl(imgUrl)
				.uploader(uploader)
				.type(type)
				.uploadDate(uploadDate)
				.cnt(cnt)
				.build();
	}
	@Builder
	public YoutubeDto(String title, String url, String iframeurl, 
			       String imgurl , String uploader, String type, Date uploadDate, Long cnt) {
		this.title = title;
		this.url = url;
		this.iframeUrl = iframeurl;
		this.imgUrl = imgurl;
		this.uploader = uploader;
		this.type = type;
		this.uploadDate = uploadDate;
		this.cnt = cnt;
	}
}
