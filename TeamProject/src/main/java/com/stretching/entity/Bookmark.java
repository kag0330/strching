package com.stretching.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOKMARK")
@ToString
public class Bookmark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@JoinColumn(name = "USER_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@JoinColumn(name = "YOUTUBE_URL")
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Youtube youtube;
	
	@Column(name = "STATUS")
	private boolean status;
	
	public Bookmark(User user, Youtube youtube) {
		this.user = user;
		this.youtube = youtube;
		this.status = true;
	}
}
