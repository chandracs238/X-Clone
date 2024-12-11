package com.pcs.x.post;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	private PostRepository postRepository;

	public PostController(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}
	
	@GetMapping("/users/posts")
	public List<Post> allPost(){
		return postRepository.findAll();
	}
}
