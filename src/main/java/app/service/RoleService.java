package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Role;
import app.repository.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		
		this.roleRepository = roleRepository;
	}
	
	
	public Role findByName(String name) {
		
		return roleRepository.findByName(name);
	}
	
	
}
