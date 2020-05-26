package app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.dto.UserRegisterDTO;
import app.entity.Role;
import app.entity.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ModelMapper modelMapper;

	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
		super();
		this.userRepository=userRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}


	public User save(User user) {
		return userRepository.save(user);
	}


	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails userDetails= userRepository.findByUsername(username);
		
		
		 if (userDetails == null) {
	            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
	     }
		 
		 userDetails.getAuthorities().size();
		
		return userDetails;
	}


	public List<User> findAll() {
		
		return userRepository.findAll();
	}


	public User findById(Long id) {
		
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
		
		
	}
	
	public List<User> findAllByUsernameStartingWith(String startWith){
		
		return userRepository.findAllByUsernameStartingWith(startWith);
		
	}
	
	
	
	
	public boolean register(UserRegisterDTO userRegisterDTO) {
		if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
			
			throw new IllegalStateException("Password and confirm password are different");
			
		}
		
		Role roleUser = roleRepository.findByName("ROLE_USER");
		User user = modelMapper.map(userRegisterDTO, User.class);
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.addRole(roleUser);
		
		
		
		if (userRepository.save(user)!=null) {
			
			return true;
			
			
		}
		
		throw new IllegalStateException("Can not register user");
		
		
		
		
	}
	
	
	
	
	
}
