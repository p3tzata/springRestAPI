package app.dto;

import javax.validation.constraints.NotNull;

import app.entity.User;

public class CommentDTO {


	private Long commentId;


	
	@NotNull
	private String content;


	private UserDTO author;
	
	
	public CommentDTO() {
		super();
		
	}










	public Long getCommentId() {
		return commentId;
	}










	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}










	public UserDTO getAuthor() {
		return author;
	}










	public void setAuthor(UserDTO author) {
		this.author = author;
	}










	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	

	
	
}
