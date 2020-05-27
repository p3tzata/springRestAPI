package app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


import app.common.util.UserUtil;
import app.dto.CommentCreateDTO;
import app.dto.CommentDTO;
import app.dto.ErrorMsgDTO;
import app.dto.PostCreateDTO;
import app.dto.PostDTO;
import app.dto.SuccessfullMsgDTO;
import app.entity.User;
import app.service.CommentService;
import app.service.PostService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentService commentService;
	private Gson gson;
	
	
	
	@Autowired
	public CommentController(CommentService commentService,Gson gson) {
		super();
		this.commentService = commentService;
		this.gson = gson;
	}
	
	
	
	
	@PostMapping("/")
	private ResponseEntity<?> createComment(Principal principal, @Valid @RequestBody CommentCreateDTO commentCreateDTO, BindingResult bindingResult) {
		return persist(principal, commentCreateDTO, bindingResult);
	}
	
	@DeleteMapping("/{commentId}")
	private ResponseEntity<?> deleteComment(@PathVariable(name = "commentId") Long commentId, Principal principal) {


		commentService.delete(commentId);
		return new  ResponseEntity<>(gson.toJson(new SuccessfullMsgDTO("Comment is deleted")),HttpStatus.OK);
		
	}
	
	
	
	
	
	private ResponseEntity<?> persist(Principal principal, @Valid @RequestBody CommentCreateDTO commentCreateDTO, BindingResult bindingResult) {
		

		if (bindingResult.hasErrors()) {
			 List<FieldError> errors = bindingResult.getFieldErrors();
		        List<ErrorMsgDTO> erroMessage = new ArrayList<>();
			
	        for (FieldError e : errors){
	        	erroMessage.add(new ErrorMsgDTO("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage()));
	        }
	        
	        return new ResponseEntity<>(gson.toJson(erroMessage),HttpStatus.BAD_REQUEST);
		}
		
		User user = UserUtil.principalToUser(principal);
		CommentDTO createPost = commentService.persistComment(user, commentCreateDTO);
		return new ResponseEntity<>(gson.toJson(createPost),HttpStatus.OK) ;
		
	}
	
	
	
	
	@GetMapping("/")
	private ResponseEntity<?> getCommentByPostId(@Param("postID") Long postId) {
		
		
		List<CommentDTO> findAllByPostId = commentService.findAllByPostId(postId);
		return new ResponseEntity<>(gson.toJson(findAllByPostId),HttpStatus.OK);
		
		
	}
	
	
	
	
}
