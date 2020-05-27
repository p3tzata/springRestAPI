package app.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.dto.CommentCreateDTO;
import app.dto.CommentDTO;
import app.dto.PostCreateDTO;
import app.dto.PostDTO;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;
import app.repository.CommentRepository;
import app.repository.PostRepository;

@Service
public class CommentService {

	
	private CommentRepository commentRepository;
	private ModelMapper modelMapper;

	
	
	@Autowired
	public CommentService(CommentRepository commentRepository,ModelMapper modelMapper) {
		super();
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;

			
	}
	
	
	public CommentDTO createTestComment(User user, Post post) {
		
		if (user==null) {
			throw new IllegalArgumentException("Can not create post. User is null.");
		}
		
		Comment testComment = new Comment();
		
		testComment.setAuthor(user);
		testComment.setPost(post);
		testComment.setContent("test content");
		
		 Comment comment = commentRepository.save(testComment);
		 CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
		
		return commentDTO;
		
		
		
	}
	
	
	public List<CommentDTO> findAllByPostId(Long postId) {
	 
	
		
		
		List<Comment> findAllByAuthor = commentRepository.findAllByPostId(postId);
		java.lang.reflect.Type listType = new TypeToken<List<CommentDTO>>(){}.getType();
		List<CommentDTO> postDtoList = modelMapper.map(findAllByAuthor,listType);
		return postDtoList;
		
	}
	
	public CommentDTO persistComment(User user,CommentCreateDTO commentCreateDTO) {
		
		
		Comment comment = modelMapper.map(commentCreateDTO, Comment.class);
		comment.setAuthor(user);
		
		Comment createdComment = commentRepository.save(comment);
		CommentDTO comentDTO = modelMapper.map(createdComment, CommentDTO.class);
		return comentDTO;
		
	}


	public CommentDTO updateComment(User user, Long id, @Valid CommentCreateDTO commentCreateDTO) {
		
		Optional<Comment> commentFindById = commentRepository.findById(id);
		
		if (commentFindById.isPresent()) {
			
			if (!commentFindById.get().getAuthor().getId().equals(user.getId()) ) {
			
				throw new IllegalStateException("You don not have perrmision to edit this Comment");
			
			}
			
		}
		
		
		Comment comment = modelMapper.map(commentCreateDTO, Comment.class);
		comment.setAuthor(user);
		comment.setCommentId(id);
		comment.setPost(commentFindById.get().getPost());
		Comment createdPost = commentRepository.save(comment);
		CommentDTO postDTO = modelMapper.map(createdPost, CommentDTO.class);
		return postDTO;
		
		
	}


	public CommentDTO findById(Long id) {
		Optional<Comment> postFindById = commentRepository.findById(id);
		if (postFindById.isPresent()) {
			CommentDTO postDTO = modelMapper.map(postFindById.get(), CommentDTO.class);
			return postDTO;
		}
		
		return null;
	}


	public void delete(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}



	
	
	
	
	
	
	
	
	
}
