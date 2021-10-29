package com.edflor.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edflor.springboot.form.app.services.RoleService;

@Component
public class RolesEditor extends PropertyEditorSupport{
	@Autowired
	private RoleService service;
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		try {
			Integer id = Integer.parseInt(text);
			setValue(service.obtenerId(id));
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			setValue(null);
		}
		
	}
	
	

}
