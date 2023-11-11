package com.stretching.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stretching.entity.Youtube;

@Repository
public interface YoutubeRepository extends JpaRepository<Youtube, String> {
	Youtube findBySeq(Long seq);
	
	Page<Youtube> findByTitleContaining(Pageable paging, String searchKey);
	
	Page<Youtube> findByUrl(Pageable paging, String searchKey);
}
