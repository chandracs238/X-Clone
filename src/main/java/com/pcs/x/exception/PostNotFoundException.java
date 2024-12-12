package com.pcs.x.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{

	public PostNotFoundException(Long id){
		super("Post not found id : " +  id);
	}
}