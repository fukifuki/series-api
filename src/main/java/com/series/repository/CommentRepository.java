package com.series.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.series.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
}
