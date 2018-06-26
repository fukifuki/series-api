package com.series.converter;

import java.util.List;

import com.series.dto.CommentDto;
import com.series.model.Comment;
import com.series.model.Series;
import com.series.model.User;

public interface CommentConverter {
	
	Comment createFromDto(CommentDto dto, User user, Series series);
	
	CommentDto createFromEntity(Comment comment);
	
	List<CommentDto> createFromEntities(List<Comment> comments);
}
