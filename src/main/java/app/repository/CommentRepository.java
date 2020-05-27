package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import app.entity.Comment;
import app.entity.Post;


//@RepositoryRestResource(path = "public/taasks")
@Repository()
public interface CommentRepository extends JpaRepository<Comment, Long> {

	
	@Query("Select c from Comment c where c.post.postId=:postId ")
	public List<Comment> findAllByPostId(@Param("postId") Long postId);
	
}
