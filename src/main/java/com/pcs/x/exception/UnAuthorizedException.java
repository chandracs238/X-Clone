package com.pcs.x.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {

	public UnAuthorizedException(Long id) {
		super("You are not authorized to access this post id : " + id);
	}
}
