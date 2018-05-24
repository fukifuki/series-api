package com.series.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentDto {
	
	@NotBlank
	@Size(max = 255,
			message = "Comment cannot be longer than 255 characters")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
