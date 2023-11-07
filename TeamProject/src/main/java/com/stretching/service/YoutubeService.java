package com.stretching.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stretching.dto.YoutubeDto;
import com.stretching.entity.Youtube;
import com.stretching.repository.YoutubeRepository;

import jakarta.transaction.Transactional;

@Service
public class YoutubeService {

	@Autowired
	private YoutubeRepository youtubeRepository;

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

	public YoutubeDto YoutubePrint(Long seq) {
		Youtube youtube = youtubeRepository.findBySeq(seq);
		YoutubeDto youtubeDto = new YoutubeDto(youtube);
	    return youtubeDto;
	}

	public List<YoutubeDto> YoutubeListPrint() {
		List<Youtube> youtubeList = youtubeRepository.findAll(Sort.by(Sort.Direction.DESC, "uploadDate"));
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
		System.out.println(pageDto.toString());

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
		System.out.println("Youtube: " + youtube);
	}

}
