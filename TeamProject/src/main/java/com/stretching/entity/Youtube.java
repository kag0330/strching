package com.stretching.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@NoArgsConstructor
@Entity
@Table(name = "YOUTUBE")

public class Youtube {
	@Column(name = "SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name = "TITLE")
	private String title;
	
	@Id
	@Column(name = "URL")
	private String url;
	
	@Column(name = "IFRAME_URL")
	private String iframeUrl;
	
	@Column(name = "IMG_URL")
	private String imgUrl;
	
	@Column(name = "UPLOADER")
	private String uploader;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "UPLOAD_DATE")
	private Date uploadDate;
	
	@Column(name = "CNT")
	private Long cnt;	

	@Builder
	public Youtube(Long seq, String title, String url, String iframeurl, 
			       String imgurl , String uploader, String type, Date uploadDate, Long cnt) {
		this.seq = seq;
		this.title = title;
		this.url = url;
		this.iframeUrl = iframeurl;
		this.imgUrl = imgurl;
		this.uploader = uploader;
		this.type = type;
		this.uploadDate = uploadDate;
		this.cnt = cnt;
	}

	public void incrementCnt() {
		this.cnt++;
	}
}
