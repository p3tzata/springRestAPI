package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;

@Service
public class PostService {

	
	private PostRepository postRepository;
	private Authentication auth;
	
	
	@Autowired
	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
		auth = SecurityContextHolder.getContext().getAuthentication();
		
		
	}
	
	
	public void createTestPost() {
		Post testPost = new Post();
		User createdByUser = ((User) auth.getPrincipal());
		testPost.setAuthor(createdByUser);
		testPost.setDescription("test desc");
		testPost.setTitle("testTitle");
		
		
		postRepository.save(testPost);
		
		
		
	}
	
	
	
	
	
}
