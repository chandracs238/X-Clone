package com.pcs.x.post;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pcs.x.exception.PostNotFoundException;
import com.pcs.x.exception.UnAuthorizedException;
import com.pcs.x.exception.UserNotFoundException;
import com.pcs.x.user.User;
import com.pcs.x.user.UserRepository;

@RestController
public class PostController {

	private PostRepository postRepository;
	private UserRepository userRepository;
	private PostModelAssembler assembler;

	public PostController(PostRepository postRepository,UserRepository userRepository, PostModelAssembler assembler) {
		super();
		this.assembler = assembler;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	public User getUser(String username) {
		User user = userRepository.findByName(username);
		if(user == null) {
			throw new UserNotFoundException(-1l);
		}
		return user;
	}
	
	@GetMapping("/{username}/posts")
	public CollectionModel<EntityModel<Post>> allPost(@PathVariable String username){
		User user = getUser(username);
//		List<Post> userPosts = postRepository.findByUser(user);
//		return userPosts;
		List<EntityModel<Post>> userPosts = postRepository.findByUser(user).stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(userPosts, linkTo(methodOn(PostController.class).allPost(user.getName())).withSelfRel());
		
	}
	
	@GetMapping("/{username}/posts/{id}")
	public EntityModel<Post> onePost(@PathVariable String username, @PathVariable Long id){
		User user = getUser(username);
		Post userPost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		if(!userPost.getUser().equals(user)) {
			throw new UnAuthorizedException(id);
		}
		return assembler.toModel(userPost);
	}
	
	@PostMapping("/{username}/posts")
	public ResponseEntity<?> createPost(@RequestBody Post post, @PathVariable String username) {
		User user = getUser(username);
		post.setUser(user);
		postRepository.save(post);
		
		EntityModel<Post> entityModel = assembler.toModel(post);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	
	
}
