package com.jmarser.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="profesores")
public class Profesor extends ClaseBaseUsuarios{

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="profesor_id", referencedColumnName = "id")
	private List<Alumno> alumnosProf = new ArrayList<>();

	public List<Alumno> getAlumnosProf() {
		return alumnosProf;
	}

	public void setAlumnosProf(List<Alumno> alumnosProf) {
		this.alumnosProf = alumnosProf;
	}
	
}
