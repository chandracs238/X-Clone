package com.pcs.x.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pcs.x.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserRepository userRepository;
	private UserModelAssembler assembler;

	public UserController(UserRepository userRepository,
			UserModelAssembler assembler) {
		super();
		this.assembler = assembler;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public List<User> allUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> newUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable Long id) {
		User savedUser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		
//		return EntityModel.of(savedUser,
//				linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
//				linkTo(methodOn(UserController.class).allUsers()).withRel("all_users"));
		return assembler.toModel(savedUser);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User updateUser) {
		User updatedUser = userRepository.findById(id)
				.map(user -> {
					user.setName(updateUser.getName());
					user.setBirthDate(updateUser.getBirthDate());
					return userRepository.save(user);
				}).orElseThrow(() -> new UserNotFoundException(id));	
		
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
