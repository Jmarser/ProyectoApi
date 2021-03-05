package com.jmarser.api.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="alumnos")
public class Alumno extends ClaseBase{

	private static final long serialVersionUID = 1L;

	@Column(name="profesor_id")
	private Long profesorId;
	
	@Column(name="tutor_id")
	private Long tutorId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="alumno_id", referencedColumnName = "id")
	private List<Ficha> fichas = new ArrayList<>();

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}

	public Long getProfesorId() {
		return profesorId;
	}

	public void setProfesorId(Long profesorId) {
		this.profesorId = profesorId;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}
	
	
}
