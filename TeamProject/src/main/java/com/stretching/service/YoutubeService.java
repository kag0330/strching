package com.stretching.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stretching.dto.YoutubeDto;
import com.stretching.entity.Youtube;
import com.stretching.repository.YoutubeRepository;

@Service
public class YoutubeService {

	private final YoutubeRepository youtubeRepository;

	public YoutubeService(YoutubeRepository youtubeRepository) {
		this.youtubeRepository = youtubeRepository;
	}

	/**
	 * CRUD 구현
	 */
	public boolean YoutubeSave(YoutubeDto youtubeDto) {
		if (youtubeRepository.findById(youtubeDto.getUrl()) != null) {
			youtubeRepository.save(Youtube.builder().url(youtubeDto.getUrl()).title(youtubeDto.getTitle())
					.iframeurl(youtubeDto.getIframeUrl()).imgurl(youtubeDto.getImgUrl()).type(youtubeDto.getType())
					.uploader(youtubeDto.getUploader()).uploadDate(youtubeDto.getUploadDate()).cnt(youtubeDto.getCnt())
					.build());
			return true;
		} else {
			return false;
		}

	}

	public List<YoutubeDto> YoutubeListPrint() {
		List<Youtube> youtubeList = youtubeRepository.findAll(Sort.by(Sort.Direction.DESC, "uploadDate"));
		List<YoutubeDto> youtubeDtoList = new ArrayList<>();
		
		for(Youtube youtube : youtubeList) {
			YoutubeDto youtubeDto = YoutubeDto.builder()
								.title(youtube.getTitle())
								.url(youtube.getUrl())
								.iframeurl(youtube.getIframeUrl())
								.imgurl(youtube.getImgUrl())
								.uploader(youtube.getUploader())
								.type(youtube.getType())
								.uploadDate(youtube.getUploadDate())
								.cnt(youtube.getCnt())
								.build();
			
			youtubeDtoList.add(youtubeDto);
		}
		return youtubeDtoList;
	}

}
