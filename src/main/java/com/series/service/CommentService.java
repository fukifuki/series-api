package com.series.service;

import java.util.List;

import com.series.dto.CommentDto;

public interface CommentService {

	CommentDto createNewComment(Long userId, Long seriesId, CommentDto commentDto);
	
	List<CommentDto> getCommentsForSeries(Long seriesId);
	
	void deleteComment(Long id);
}
