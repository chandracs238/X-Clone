package com.pcs.x.user;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.pcs.x.post.Post;
import com.pcs.x.post.PostRepository;
import com.pcs.x.todo.Todo;
import com.pcs.x.todo.TodoRepository;

@Component
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, PostRepository postRepository, TodoRepository todoRepository){
		return arg -> {
			User baba = userRepository.save(new User("baba", LocalDate.now().minusYears(20)));
			User karun = userRepository.save(new User("karun", LocalDate.now().minusYears(20)));
			User dinesh = userRepository.save(new User("dinesh", LocalDate.now().minusYears(20)));
			User prakash = userRepository.save(new User("prakash", LocalDate.now().minusYears(20)));
			
			postRepository.save(new Post(
				    "How to Secure Your Website from Hackers",
				    "Website security is crucial in the modern digital age. Cyber threats like phishing, SQL injection, and ransomware are on the rise. To protect your website, start by enabling HTTPS for encrypted connections. Regularly update your software to patch vulnerabilities. Use strong passwords and multi-factor authentication to secure admin accounts. Conduct regular security audits and penetration testing to identify potential risks. Consider installing a web application firewall (WAF) to filter malicious traffic. Monitor your logs for unusual activity and create a robust incident response plan. Remember, prevention is better than cure in cybersecurity.",
				    baba
				));

				postRepository.save(new Post(
				    "The Art of Ethical Hacking: Learn to Defend",
				    "Ethical hacking is an essential skill for any cybersecurity professional. It involves probing systems for vulnerabilities before malicious hackers exploit them. Ethical hackers use tools like Nmap, Wireshark, and Metasploit to identify risks. Before beginning, ensure you have proper authorization and follow the guidelines of your organization. Ethical hacking isn't just about technical skills; understanding human psychology, like social engineering tactics, is equally important. By learning ethical hacking, you not only improve security but also open career opportunities in cybersecurity, a field in high demand.",
				    karun
				));

				postRepository.save(new Post(
				    "The Future of Web Development in 2025",
				    "Web development is evolving rapidly. By 2025, technologies like AI, Web3, and quantum computing will revolutionize the industry. AI will enhance user personalization, making websites smarter and more adaptive. Web3 will decentralize the internet, empowering users with more control over their data. Quantum computing promises faster and more secure transactions. Developers should focus on learning modern frameworks like React, Vue, and Svelte. Embracing DevOps and continuous integration will remain key to efficient deployment. The future is bright, but it demands constant learning and adaptation from developers.",
				    dinesh
				));

				postRepository.save(new Post(
				    "Building Scalable Applications: A Guide for Developers",
				    "Scalability is the hallmark of a successful application. To build scalable systems, start by designing with modularity in mind. Break monolithic applications into microservices to distribute workloads efficiently. Choose the right database based on your use case—SQL for structured data or NoSQL for flexibility. Implement caching mechanisms like Redis or Memcached to improve performance. Use cloud services such as AWS or Azure to scale dynamically based on traffic. Monitor your system's health using tools like Prometheus and Grafana. Finally, write clean, efficient code to optimize resource usage.",
				    prakash
				));

				postRepository.save(new Post(
				    "Mastering Java for Full-Stack Development",
				    "Java remains one of the most popular programming languages for full-stack development. To master it, start by learning the basics: syntax, object-oriented programming, and core APIs. Move on to building backends using Spring Boot, the most widely used Java framework. For front-end integration, explore frameworks like React or Angular, which pair well with Java-based REST APIs. Learn to work with databases such as MySQL or MongoDB. Deploy your applications on platforms like Heroku or AWS. Remember to write unit tests using JUnit to ensure code quality. Java mastery opens doors to robust, enterprise-grade software development.",
				    baba
				));
				
			todoRepository.save(new Todo("Learn Java", LocalDate.now(), LocalDate.now().plusDays(3), false, baba));
			todoRepository.save(new Todo("Learn Java", LocalDate.now(), LocalDate.now().plusDays(3), false, karun));
			todoRepository.save(new Todo("Learn Java", LocalDate.now(), LocalDate.now().plusDays(3), false, dinesh));
			todoRepository.save(new Todo("Learn Java", LocalDate.now(), LocalDate.now().plusDays(3), false, prakash));
			
			userRepository.findAll().forEach(user -> log.info("Preload " + user));
		};
	}
}
