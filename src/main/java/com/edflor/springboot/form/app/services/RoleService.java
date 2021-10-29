package com.edflor.springboot.form.app.services;

import java.util.List;

import com.edflor.springboot.form.app.models.domain.Role;

public interface RoleService {
	public List<Role> listar();
	public Role obtenerId(Integer id);
}
