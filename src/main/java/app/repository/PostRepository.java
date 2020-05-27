package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import app.entity.Post;
import app.entity.User;


@Repository()
public interface PostRepository extends JpaRepository<Post, Long> {
	
	
	@Query("Select p from Post p where p.author=:author ")
	public List<Post> findAllByAuthor(@Param("author") User author);
	
	

}
