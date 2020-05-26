package app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import app.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	

	
	@RestResource(path = "baseSearch", rel = "baseSearchRel")
	public User findByUsername(String username);


	@Override
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RestResource(exported = false)
	void deleteById(Long aLong);
	
	
	public List<User> findAllByUsernameStartingWith(String startWith);
	
	
	
	
}
