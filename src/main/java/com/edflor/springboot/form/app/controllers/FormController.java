package com.edflor.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.edflor.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {

	@GetMapping("/form")
	public String formModel(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}

	@PostMapping("/form")
	public String procesar(Usuario usuario, Model model) {
		model.addAttribute("titulo", "Resultado del Form");
		model.addAttribute("usuario", usuario);
		return "resultado";
	}

}
