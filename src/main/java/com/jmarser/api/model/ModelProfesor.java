package com.jmarser.api.model;

import com.jmarser.api.entity.Profesor;

public class ModelProfesor extends ModelBase{
	

	public ModelProfesor(Profesor profesor) {
		this.setId(profesor.getId());
		this.setNombre(profesor.getNombre());
		this.setPrimerApellido(profesor.getPrimerApellido());
		this.setSegundoApellido(profesor.getSegundoApellido());
		this.setEmail(profesor.getEmail());
	}
}
