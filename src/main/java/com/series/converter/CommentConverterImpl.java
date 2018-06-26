package com.series.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.series.dto.CommentDto;
import com.series.model.Comment;
import com.series.model.Series;
import com.series.model.User;
import com.series.repository.CommentRepository;

@Component
public class CommentConverterImpl implements CommentConverter {

	CommentRepository commentRepository;
	
	@Override
	public Comment createFromDto(CommentDto dto, User user, Series series) {
		Comment comment = new Comment();
		comment.setUser(user);
		comment.setSeries(series);
		
		return commentRepository.save(comment);
	}
	
	@Override
	public CommentDto createFromEntity(Comment comment) {
		CommentDto dto = new CommentDto();
		dto.setId(comment.getId());
		dto.setBody(comment.getBody());
		
		return dto;
	}

	@Override
	public List<CommentDto> createFromEntities(List<Comment> comments) {
		List<CommentDto> dtos = new ArrayList<CommentDto>();
		
		comments.forEach((comment) -> {
			CommentDto dto = new CommentDto();
			dto.setId(comment.getId());
			dto.setBody(comment.getBody());
		});
		
		return dtos;
	}

}
