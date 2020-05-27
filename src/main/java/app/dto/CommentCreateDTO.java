package app.dto;




public class CommentCreateDTO {

	private Long commentId;

	private PostDTO post;
	
	

	private String content;


	public CommentCreateDTO() {
		super();
		
	}





	public Long getCommentId() {
		return commentId;
	}





	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}





	public PostDTO getPost() {
		return post;
	}


	public void setPost(PostDTO post) {
		this.post = post;
	}



	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
	

	
	
}
