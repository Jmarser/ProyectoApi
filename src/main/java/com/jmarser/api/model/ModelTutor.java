package com.jmarser.api.model;

import com.jmarser.api.entity.Tutor;

public class ModelTutor extends ModelBase{

	private String empresa;

	public ModelTutor(Tutor tutor) {
		this.setId(tutor.getId());
		this.setNombre(tutor.getNombre());
		this.setPrimerApellido(tutor.getPrimerApellido());
		this.setSegundoApellido(tutor.getSegundoApellido());
		this.setEmail(tutor.getEmail());
		this.setEmpresa(tutor.getEmpresa());
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	
}
