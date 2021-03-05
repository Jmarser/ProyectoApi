package com.jmarser.api.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="tutores")
public class Tutor extends ClaseBase{

	private static final long serialVersionUID = 1L;

	private String empresa;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tutor_id", referencedColumnName = "id")
	private List<Alumno> alumnosTutor = new ArrayList<>();

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public List<Alumno> getAlumnosTutor() {
		return alumnosTutor;
	}

	public void setAlumnosTutor(List<Alumno> alumnosTutor) {
		this.alumnosTutor = alumnosTutor;
	}
	
}
