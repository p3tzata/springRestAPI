package app.common.util;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import app.entity.User;

public class UserUtil {

	
	static public User principalToUser(Principal principal) {
		
		 User user=null;
			if (principal instanceof UsernamePasswordAuthenticationToken) {
			    UsernamePasswordAuthenticationToken principalToken = ((UsernamePasswordAuthenticationToken) principal);
			     user = (User)  principalToken.getPrincipal();
			    
			}
			
			if (user==null) {
				throw new IllegalArgumentException("Can not convert Principal to User");
			}
			
			return user;
		
		
	}
	
}
