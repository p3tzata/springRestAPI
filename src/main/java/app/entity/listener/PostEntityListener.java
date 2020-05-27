package app.entity.listener;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import app.entity.Post;


@Component
public class PostEntityListener {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PrePersist
	@PreUpdate
	void beforePersist(Post post) {
	
		LocalDateTime now = LocalDateTime.now();
		post.setCreatedDate(now);
	}
	
}
