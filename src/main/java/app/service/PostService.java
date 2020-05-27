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

import app.dto.PostCreateDTO;
import app.dto.PostDTO;
import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;

@Service
public class PostService {

	
	private PostRepository postRepository;
	private ModelMapper modelMapper;

	
	
	@Autowired
	public PostService(PostRepository postRepository,ModelMapper modelMapper) {
		super();
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;

			
	}
	
	
	public PostDTO createTestPost(User user) {
		
		if (user==null) {
			throw new IllegalArgumentException("Can not create post. User is null.");
		}
		
		Post testPost = new Post();
		
		testPost.setAuthor(user);
		testPost.setDescription("test desc");
		testPost.setTitle("testTitle");
		Post post = postRepository.save(testPost);
		PostDTO postDTO = modelMapper.map(post,PostDTO.class);
		
		return postDTO;
		
		
		
	}
	
	
	public List<PostDTO> findAllByAuthor(User user) {
	 
		
		List<Post> findAllByAuthor = postRepository.findAllByAuthor(user);
		java.lang.reflect.Type listType = new TypeToken<List<PostDTO>>(){}.getType();
		List<PostDTO> postDtoList = modelMapper.map(findAllByAuthor,listType);
		return postDtoList;
		
	}
	
	public PostDTO createPost(User user,PostCreateDTO postCreateDTO) {
		
		
		Post post = modelMapper.map(postCreateDTO, Post.class);
		post.setAuthor(user);
		Post createdPost = postRepository.save(post);
		PostDTO postDTO = modelMapper.map(createdPost, PostDTO.class);
		return postDTO;
		
	}


	public PostDTO persist(User user, PostCreateDTO postCreateDTO) {
		
		if (postCreateDTO.getPostId()!=null) {
		
		Optional<Post> postFindById = postRepository.findById(postCreateDTO.getPostId());
		
		if (postFindById.isPresent()) {
			
			if (!postFindById.get().getAuthor().getId().equals(user.getId()) ) {
			
				throw new IllegalStateException("You don not have perrmision to edit this Post");
			
			}
			
		}
		
		}
		
		
		Post post = modelMapper.map(postCreateDTO, Post.class);
		post.setAuthor(user);
		Post createdPost = postRepository.save(post);
		PostDTO postDTO = modelMapper.map(createdPost, PostDTO.class);
		return postDTO;
		
		
	}


	public PostDTO findById(Long id) {
		Optional<Post> postFindById = postRepository.findById(id);
		if (postFindById.isPresent()) {
			PostDTO postDTO = modelMapper.map(postFindById.get(), PostDTO.class);
			return postDTO;
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
}
