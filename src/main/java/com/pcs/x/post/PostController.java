package com.pcs.x.post;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pcs.x.exception.UserNotFoundException;
import com.pcs.x.user.User;
import com.pcs.x.user.UserRepository;

@RestController
public class PostController {

	private PostRepository postRepository;
	private UserRepository userRepository;

	public PostController(PostRepository postRepository,UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/{username}/posts")
	public List<Post> allPost(@PathVariable String username){
		User user = userRepository.findByName(username);
		
		if(user == null) {
			throw new UserNotFoundException(-1l);
		}
		
		List<Post> userPosts = postRepository.findByUser(user);
		return userPosts;
	}
}
