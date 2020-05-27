package app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import app.common.util.UserUtil;
import app.dto.ErrorMsgDTO;
import app.dto.PostCreateDTO;
import app.dto.PostDTO;
import app.entity.Post;
import app.entity.User;
import app.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;
	private Gson gson;
	
	
	
	@Autowired
	public PostController(PostService postService,Gson gson) {
		super();
		this.postService = postService;
		this.gson = gson;
	}




	@PostMapping("/createTestPost")
	private ResponseEntity<?> addTestPost(Principal principal) {
		/*
		 User createdByUser=null;
		if (principal instanceof UsernamePasswordAuthenticationToken) {
		    UsernamePasswordAuthenticationToken principalToken = ((UsernamePasswordAuthenticationToken) principal);
		     createdByUser = (User)  principalToken.getPrincipal();
		    
		}
		*/
		User user = UserUtil.principalToUser(principal);
		
		PostDTO createTestPost = postService.createTestPost(user);
		
		if (createTestPost!=null) {
			return new ResponseEntity<>(gson.toJson(createTestPost),HttpStatus.CREATED);
		}
		
		throw new IllegalStateException("Can not create post ");
			
	}
	
	
	
	@GetMapping("/getMyPost")
	private ResponseEntity<?> getMyPost(Principal principal) {
		
		User user = UserUtil.principalToUser(principal);
		List<PostDTO> findAllByAuthor = postService.findAllByAuthor(user);
		return new ResponseEntity<>(gson.toJson(findAllByAuthor),HttpStatus.OK);
		
		
	}
	
	/*
	@PostMapping("/")
	private ResponseEntity<?> createPost(Principal principal, @Valid @RequestBody PostCreateDTO postCreateDTO, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			 List<FieldError> errors = bindingResult.getFieldErrors();
		        List<ErrorMsgDTO> erroMessage = new ArrayList<>();
			
	        for (FieldError e : errors){
	        	erroMessage.add(new ErrorMsgDTO("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage()));
	        }
	        
	        return new ResponseEntity<>(gson.toJson(erroMessage),HttpStatus.BAD_REQUEST);
		}
		
		User user = UserUtil.principalToUser(principal);
		PostDTO createPost = postService.createPost(user,postCreateDTO);
		return new ResponseEntity<>(gson.toJson(createPost),HttpStatus.OK) ;
	}
	*/
	
	@PostMapping(value = {"/","/{postId}"})
	private ResponseEntity<?> createPost(Principal principal, @Valid @RequestBody PostCreateDTO postCreateDTO, BindingResult bindingResult) {
		
		return persist(principal, postCreateDTO, bindingResult);
	}
	

	/*
	@PostMapping("/{postId}")
	private ResponseEntity<?> updatePost(Principal principal, @Valid @RequestBody PostCreateDTO postCreateDTO, BindingResult bindingResult) {
		
		return persist(principal, postCreateDTO, bindingResult);
	}
	*/
	
	private ResponseEntity<?> persist(Principal principal, @Valid @RequestBody PostCreateDTO postCreateDTO, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			 List<FieldError> errors = bindingResult.getFieldErrors();
		        List<ErrorMsgDTO> erroMessage = new ArrayList<>();
			
	        for (FieldError e : errors){
	        	erroMessage.add(new ErrorMsgDTO("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage()));
	        }
	        
	        return new ResponseEntity<>(gson.toJson(erroMessage),HttpStatus.BAD_REQUEST);
		}
		
		User user = UserUtil.principalToUser(principal);
		
		
		PostDTO createPost = postService.persist(user,postCreateDTO);
		
		return new ResponseEntity<>(gson.toJson(createPost),HttpStatus.OK) ;
	}
	
	
	@GetMapping("/{id}")
	private ResponseEntity<?> getPost(@PathVariable Long id,Principal principal) {
		
		PostDTO postDTO = postService.findById(id);
		return new ResponseEntity<>(gson.toJson(postDTO),HttpStatus.OK) ;
	}
	
	
	
	
	
	
	
}

