package com.pcs.x.home;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcs.x.post.Post;
import com.pcs.x.post.PostRepository;

@RestController
public class HomeController {

	private PostRepository postRepository;
//	private UserRepository userReopsitory;
	
	public HomeController(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
//		this.userReopsitory = userReopsitory;
	}

	@GetMapping("/home")
	public List<Post> allPosts(){
		return postRepository.findAll();
	}
}
