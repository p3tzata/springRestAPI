package app.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "role")
public final class Role implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	private final String name;

	public Role() {
		super();
		this.name = "";
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



	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }
	
	
	@Override
	public boolean equals(Object obj) {

		Role obj_BaseDTO = (Role) obj;
		
		if (this.getId() ==null || obj_BaseDTO.getId()==null) {
			return false;
		}
		
		
		if (Long.compare(this.getId(), obj_BaseDTO.getId()) == 0) {
			return true;
		} else {
			return false;
		}
		
		
		
	}
	

	
	
	
}
