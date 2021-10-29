package com.edflor.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edflor.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImp implements PaisService {
	
	private List<Pais> lista;

	public PaisServiceImp() {
		this.lista = Arrays.asList(
				new Pais (1, "ES", "Espana"), 
				new Pais (2, "MX", "Mexico"), 
				new Pais (3, "CL", "Chile"), 
				new Pais (4, "PE", "Peru"), 
				new Pais (5, "CO", "Colombia"), 
				new Pais (6, "AR", "Argentina"), 
				new Pais (7, "VN", "Venezuela"));
		
	}

	@Override
	public List<Pais> listar() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		// TODO Auto-generated method stub
		Pais resultado = null;
		for(Pais pais: this.lista) {
			if (id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
