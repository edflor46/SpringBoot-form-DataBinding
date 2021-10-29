package com.edflor.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.HashMap;
//import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.edflor.springboot.form.app.editors.NombreMayusculaEditors;
import com.edflor.springboot.form.app.editors.PaisPropertyEditor;
import com.edflor.springboot.form.app.editors.RolesEditor;
import com.edflor.springboot.form.app.models.domain.Pais;
import com.edflor.springboot.form.app.models.domain.Role;
import com.edflor.springboot.form.app.models.domain.Usuario;
import com.edflor.springboot.form.app.services.PaisService;
import com.edflor.springboot.form.app.services.RoleService;
import com.edflor.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RolesEditor roleEditor;

	@InitBinder
	public void initBindir(WebDataBinder binder) {
		binder.addValidators(validador);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditors());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditors());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}

	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLES_MODERATOR");

		return roles;

	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLES_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("Espana", "Mexico", "Chile", "Suecia", "Peru", "Colombia", "Argentina", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("ES", "Espana");
		paises.put("MX", "Mexico");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Peru");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");

		return paises;
	}

	@GetMapping("/form")
	public String formModel(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Eduardo");
		usuario.setApellido("Flores");
		usuario.setIdentificador("12.232.222-L");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algun valor ****");
		usuario.setPais(new Pais(2, "MX", "Mexico"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
		
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
		//validador.validate(usuario, result);
		//model.addAttribute("titulo", "Resultado del Form");

		if (result.hasErrors()) {
			/*
			 * Map<String, String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(err -> { errores.put(err.getField(),
			 * "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()
			 * )); }); model.addAttribute("error", errores);
			 */
			model.addAttribute("titulo", "Resultado del Form");
			return "form";
		}

		//model.addAttribute("usuario", usuario);
		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
		
		if (usuario == null) {
			return "redirect:/form";
		}
		
		model.addAttribute("titulo", "Resultado del Form");
		status.setComplete();
		return "resultado";
	}

}
