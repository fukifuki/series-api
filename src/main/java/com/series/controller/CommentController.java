package com.series.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.CommentDto;
import com.series.dto.UserDto;
import com.series.model.Comment;
import com.series.model.User;
import com.series.service.CommentService;
import com.series.service.SeriesService;
import com.series.service.UserService;

@RestController
@RequestMapping("/series")
public class CommentController {
	
//	@Autowired
//	SeriesRepository seriesRepository;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	SeriesService seriesService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{seriesId}/comments")
	public List<CommentDto> getCommentsForSeries(@PathVariable("seriesId") Long seriesId) {

//		TODO Move this series finding into separate method... see AOP etc.
//      find series
//		Series series = null;
//		try {
//			series = ...
//		} catch (ResourceNotFoundException e) {
//			return null;
//		}
		
//		find comments for a given series
//      TODO Maybe order comments by date (or later by votes if I add that option)...
		
		return commentService.getCommentsForSeries(seriesId);
	}
	
//	What should be a return value for this method if there should be any return value?
	@PostMapping("/{seriesId}/comments")
	public Comment addCommentToSeries(@PathVariable("seriesId") Long seriesId, 
								      @Valid @RequestBody CommentDto commentDto, 
								      Principal principal) {
		
		UserDto user = userService.findByUsername(principal.getName());
		Long userId = user.getId();
//		find series
//		Series series = null;
//		try {
//			series = seriesService.findById(seriesId);
//		} catch (ResourceNotFoundException e) {
////			...
//		}
		
		return commentService.createNewComment(userId, seriesId, commentDto);
	}
	
	
}
