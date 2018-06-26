package com.series.service;

import java.util.List;

import com.series.dto.CommentDto;
import com.series.model.Comment;
import com.series.model.User;

public interface CommentService {

	CommentDto createNewComment(Long userId, Long seriesId, CommentDto commentDto);
	
	List<CommentDto> getCommentsForSeries(Long seriesId);
}
