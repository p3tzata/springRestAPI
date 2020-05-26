package app.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import app.entity.User;

@Component
public class UserEntityListener {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PrePersist
	@PreUpdate
	void beforePersist(User user) {
	//	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}
	
}
