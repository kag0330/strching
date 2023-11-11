package com.stretching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stretching.entity.Bookmark;
import com.stretching.entity.User;
import com.stretching.entity.Youtube;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Bookmark findByUserAndYoutube(User user, Youtube youtube);
	
	List<Bookmark> findByUser(User user);
	
	List<Bookmark> findByYoutube_Url(String searchUrl);
}
