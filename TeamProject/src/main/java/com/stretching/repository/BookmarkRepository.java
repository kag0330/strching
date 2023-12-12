package com.stretching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.stretching.entity.Bookmark;
import com.stretching.entity.User;
import com.stretching.entity.Youtube;

import jakarta.transaction.Transactional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Bookmark findByUserAndYoutube(User user, Youtube youtube);
	
	List<Bookmark> findByUser(User user);
	
	List<Bookmark> findByYoutube_Url(String searchUrl);
	
	@Transactional
	@Modifying
	void deleteByYoutube(Youtube youtube);
}
