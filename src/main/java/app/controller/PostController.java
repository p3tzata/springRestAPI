package app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;
	
	
	
	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}




	@PostMapping("/createTestPost")
	private void addTestPost() {
		
		postService.createTestPost();
		
	}
	
}

