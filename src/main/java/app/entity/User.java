package app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;



import app.entity.listener.UserEntityListener;


 

@Entity
@Table(name="user")
@EntityListeners(UserEntityListener.class)
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false,unique = true)
	String username;

	@Column(nullable = false)
	
	String password;
	
	
	@ManyToMany
	private  Set<Role> roles;
	private  boolean accountNonExpired;
	private  boolean accountNonLocked;
	private  boolean credentialsNonExpired;
	private  boolean enabled;
	
	


	public User() {
		super();
		roles=new HashSet<>();
	}


	
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}




	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}




	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}




	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}




	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}




	public Set<Role> getAuthorities() {
		return roles;
	}


	public boolean getAccountNonExpired() {
		return accountNonExpired;
	}


	public boolean getAccountNonLocked() {
		return accountNonLocked;
	}


	public boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public boolean getEnabled() {
		return enabled;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}




	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}




	@Override
	public boolean isAccountNonLocked() {
		
		return this.accountNonLocked;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		
		return this.credentialsNonExpired;
	}




	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	
	
	
	
	
	
}
