package com.pcs.x.user;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.pcs.x.post.Post;
import com.pcs.x.post.PostRepository;

@Component
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, PostRepository postRepository){
		return arg -> {
			User baba = userRepository.save(new User("baba", LocalDate.now().minusYears(20)));
			User karun = userRepository.save(new User("karun", LocalDate.now().minusYears(20)));
			User dinesh = userRepository.save(new User("dinesh", LocalDate.now().minusYears(20)));
			User prakash = userRepository.save(new User("prakash", LocalDate.now().minusYears(20)));
			
			postRepository.save(new Post("Hello", baba));
			postRepository.save(new Post("Hello", baba));
			postRepository.save(new Post("Hello", karun));
			postRepository.save(new Post("Hello", dinesh));
			postRepository.save(new Post("Hello", prakash));			
			
			userRepository.findAll().forEach(user -> log.info("Preload " + user));
		};
	}
}
