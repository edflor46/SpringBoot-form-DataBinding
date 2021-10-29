package com.edflor.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edflor.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceOImpl implements RoleService {

	private List<Role> roles;
	
	public RoleServiceOImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1, "Adminsitrador", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
		this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
	}
	@Override
	public List<Role> listar() {
		return roles;
	}

	@Override
	public Role obtenerId(Integer id) {
		Role resultado = null;
		
		for(Role role: roles) {
			if (id == role.getId()) {
				resultado = role;
				break;
			}
		}
		
		return resultado;
	}


}
