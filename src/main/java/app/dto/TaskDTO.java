package app.dto;



public class TaskDTO {

	Long id;
	
	String name;
	
	String description;

	
	public TaskDTO() {
		super();
	}

	
	
	
	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
