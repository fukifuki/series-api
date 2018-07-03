package com.series.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.converter.CommentConverter;
import com.series.dto.CommentDto;
import com.series.model.Comment;
import com.series.model.Series;
import com.series.model.User;
import com.series.repository.CommentRepository;
import com.series.repository.SeriesRepository;
import com.series.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	SeriesRepository seriesRepository;
	
	@Autowired
	CommentConverter commentConverter;
	
	@Override
	public CommentDto createNewComment(Long userId, Long seriesId, CommentDto commentDto) {
		
//		should I handle nonexistant series case here or elsewhere?
//		TODO there should probably be one central place with the method for retrieving series
		Series series = seriesRepository.getOne(seriesId);
		User user = userRepository.getOne(userId);
		
//		TODO use converter class for this purpose...
		Comment comment = new Comment();
//		setUser and setSeries methods should probably be called from some method whose purpose would be consistent comments db updating
		comment.setUser(user);
		comment.setSeries(series);
		comment.setBody(commentDto.getBody());
		
		return commentConverter.createFromEntity(commentRepository.save(comment));
	}

	@Override
	public List<CommentDto> getCommentsForSeries(Long seriesId) {
		Series series = seriesRepository.getOne(seriesId);
		Set<Comment> comments = series.getComments();
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		comments.forEach(comment -> {
			CommentDto dto = new CommentDto();
			dto.setBody(comment.getBody());
			commentDtos.add(dto);
		});
		
		return commentDtos;
	}
	
	@Override
	public void deleteComment(Long id) {
		Comment comment = commentRepository.getOne(id);
		commentRepository.delete(comment);
	}
}
