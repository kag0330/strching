package com.stretching.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stretching.entity.Youtube;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class YoutubeDto {
	
	private Long seq;
	
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
		this.seq = entity.getSeq();
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
//				.seq(this.seq)
				.title(this.title)
				.url(this.url)
				.iframeurl(this.iframeUrl)
				.imgurl(this.imgUrl)
				.uploader(this.uploader)
				.type(this.type)
				.uploadDate(new Date())
				.cnt(0L)
				.build();
	}
	
	public Youtube toEntity(Long seq) {
		return Youtube.builder()
				.seq(seq)
				.title(this.title)
				.url(this.url)
				.iframeurl(this.iframeUrl)
				.imgurl(this.imgUrl)
				.uploader(this.uploader)
				.type(this.type)
				.uploadDate(new Date())
				.cnt(0L)
				.build();
	}
	
}
