package com.stretching.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stretching.KeyValuePair;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Youtube {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	private String title;
	private String url;
	private String iframe_url;
	private String img_url;
	private String uploader;
	private String type;
	private Date upload_date;
	private int cnt;

	public List<KeyValuePair> toKeyValuePairs() {
		List<KeyValuePair> kvp = new ArrayList<>();
		kvp.add(new KeyValuePair("title", this.title));
		kvp.add(new KeyValuePair("url", this.url));
		kvp.add(new KeyValuePair("iframe_url", this.iframe_url));
		kvp.add(new KeyValuePair("img_url", this.img_url));
		kvp.add(new KeyValuePair("uploader", this.uploader));
		return kvp;
	}
}
