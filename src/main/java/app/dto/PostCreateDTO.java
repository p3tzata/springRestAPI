package app.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;



public class PostCreateDTO {
	
	
	private Long postId;

	@NotNull
	@Size(min=1,max=50)
	private String description;
	@NotNull
	@Size(min = 1,max = 50)
	private String title;
	@NotNull
	@Size(min=1,max=50)
	private String url;
	


	
	
	
	
	public PostCreateDTO() {
		super();
	
	}


	
	


	public Long getPostId() {
		return postId;
	}






	public void setPostId(Long postId) {
		this.postId = postId;
	}






	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	
	
}
