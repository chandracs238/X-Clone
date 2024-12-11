package com.pcs.x.user;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository){
		return arg -> {
			userRepository.save(new User("baba", LocalDate.now().minusYears(20)));
			userRepository.save(new User("karun", LocalDate.now().minusYears(20)));
			userRepository.save(new User("dinesh", LocalDate.now().minusYears(20)));
			userRepository.save(new User("prakash", LocalDate.now().minusYears(20)));
			
//			postRepository.save(new Post("Hello", baba));
//			postRepository.save(new Post("Hello", baba));
//			postRepository.save(new Post("Hello", karun));
//			postRepository.save(new Post("Hello", dinesh));
//			postRepository.save(new Post("Hello", prakash));
//			
			
			userRepository.findAll().forEach(user -> log.info("Preload " + user));
		};
	}
}
