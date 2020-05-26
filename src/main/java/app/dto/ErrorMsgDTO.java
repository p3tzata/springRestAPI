package app.dto;

public class ErrorMsgDTO {
	
	private String errorMsg;
	

	public ErrorMsgDTO(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public String getError() {
		return errorMsg;
	}

	public void setError(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	
	

}
