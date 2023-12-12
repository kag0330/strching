package com.stretching.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stretching.dto.YoutubeDto;
import com.stretching.entity.Bookmark;
import com.stretching.entity.User;
import com.stretching.entity.Youtube;
import com.stretching.repository.BookmarkRepository;
import com.stretching.repository.UserRepository;
import com.stretching.repository.YoutubeRepository;

import jakarta.transaction.Transactional;

@Service
public class YoutubeService {
	@Autowired
	private YoutubeRepository youtubeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookmarkRepository bookmarkRepository;
	/**
	 * 구현
	 */
	public boolean YoutubeSave(YoutubeDto youtubeDto) {
		if (youtubeRepository.findById(youtubeDto.getUrl()) != null) {
			youtubeRepository.save(youtubeDto.toEntity());
			return true;
		} else {
			return false;
		}

	}

	public YoutubeDto youtubePrint(Long seq) {
		Youtube youtube = youtubeRepository.findBySeq(seq);
		YoutubeDto youtubeDto = new YoutubeDto(youtube);
	    return youtubeDto;
	}
	public List<YoutubeDto> youtubeListPrintByDate() {
		List<Youtube> youtubeList = youtubeRepository.findAll(Sort.by(Sort.Direction.DESC, "uploadDate"));
		List<YoutubeDto> youtubeDtoList = new ArrayList<>();

		for (Youtube youtube : youtubeList) {
			youtubeDtoList.add(new YoutubeDto(youtube));
		}
		return youtubeDtoList;
	}
	
	public List<YoutubeDto> youtubeListPrintByCnt() {
		List<Youtube> youtubeList = youtubeRepository.findAll(Sort.by(Sort.Direction.DESC, "cnt"));
		System.out.println(youtubeList.toString());
		List<YoutubeDto> youtubeDtoList = new ArrayList<>();

		for (Youtube youtube : youtubeList) {
			youtubeDtoList.add(new YoutubeDto(youtube));
		}
		return youtubeDtoList;
	}

	public List<YoutubeDto> youtubeListSave(List<YoutubeDto> youtubeList) {
		for (YoutubeDto youtube : youtubeList) {
			youtubeRepository.save(youtube.toEntity(youtubeSeqMax()));
		}
		return youtubeList;
	}

	public Page<YoutubeDto> paging(Pageable pageable) {
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 10;
		Page<Youtube> pages = youtubeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by("seq").descending()));

		Page<YoutubeDto> pageDto = pages.map(postPage -> new YoutubeDto(postPage));
		

		return pageDto;
	}
	public Page<YoutubeDto> pagingSearch(Pageable pageable, String searchKey) {
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 10;
		Pageable paging = PageRequest.of(page, pageLimit, Sort.by("seq").descending());
		Page<Youtube> pages = null;
		if(searchKey == null || searchKey == "") {
			pages = youtubeRepository.findAll(paging);
		}else {
			pages = youtubeRepository.findByTitleContaining(paging, searchKey);
		}
		
		

		Page<YoutubeDto> pageDto = pages.map(postPage -> new YoutubeDto(postPage));
		System.out.println(pageDto.toString());

		return pageDto;
	}
	
	@Transactional
    public Page<YoutubeDto> pagingUserBookmark(Pageable pageable, User user) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 10;
        Pageable paging = PageRequest.of(page, pageLimit, Sort.by("seq").descending());
 
        List<Bookmark> bookmarklists = bookmarkRepository.findByUser(user);

        List<Youtube> userBookmarks = bookmarklists.stream()
                .map(Bookmark::getYoutube)
                .collect(Collectors.toList());

        // 페이지 구성
        int start = (int) paging.getOffset();
        int end = Math.min((start + paging.getPageSize()), userBookmarks.size());
        List<Youtube> sublist = userBookmarks.subList(start, end);

        Page<Youtube> userBookmarkPage = new PageImpl<>(sublist, paging, userBookmarks.size());

        // YoutubeDto로 매핑
        Page<YoutubeDto> pageDto = userBookmarkPage.map(YoutubeDto::new);

        return pageDto;
    }

	/**
	 * Youtube테이블의 Seq의 최대값을 읽어 반환해주는 클래스
	 */
	public Long youtubeSeqMax() {
		List<Youtube> youtubeList = youtubeRepository.findAll();

		if (youtubeList.isEmpty()) {
			return 1L;
		} else {
			Long maxSeq = youtubeList.stream().filter(youtube -> youtube.getSeq() != null).map(Youtube::getSeq)
					.max(Long::compare).orElse(1L);
			return maxSeq + 1;
		}
	}
	@Transactional
	public void updateCnt(Long seq) {
		Youtube youtube = youtubeRepository.findBySeq(seq);
		youtube.incrementCnt();
	}
	
	@Transactional
	public void onBookmark(User user, Long seq) {
		Youtube youtube = youtubeRepository.findBySeq(seq);
		if(youtube != null) {
			if(bookmarkRepository.findByUserAndYoutube(user, youtube) == null) {
				Bookmark bookmark = new Bookmark(user, youtube);
				bookmarkRepository.save(bookmark);
			}else {
				Bookmark bookmark = bookmarkRepository.findByUserAndYoutube(user, youtube);
				bookmarkRepository.delete(bookmark);
			}	
		}
	}
	public boolean checkBookmark(User user,Long seq) {
		Youtube youtube = youtubeRepository.findBySeq(seq);
		if(bookmarkRepository.findByUserAndYoutube(user, youtube) != null) {
			System.out.println("5");
			return true;
		}else {
			System.out.println("6");
			return false;
		}
	}

	public boolean delete(Long seq) {
		try {
			Youtube youtube = youtubeRepository.findBySeq(seq);
			bookmarkRepository.deleteByYoutube(youtube);
			youtubeRepository.deleteBySeq(seq);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
