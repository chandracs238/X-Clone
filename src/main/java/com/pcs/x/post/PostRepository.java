package com.pcs.x.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcs.x.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findByUser(User user);

}
