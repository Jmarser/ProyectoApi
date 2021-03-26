package com.jmarser.api.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="fichas")
public class Ficha implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 1000)
	private String descripcion;
	
	@Column(length = 1000)
	private String observaciones;
	
	@Column(name = "horas")
	private int horas;

	@Column(name="alumno_id")
	private Long alumnoId;
	
	@Column(name="firma_profesor")
	private boolean firmaProf;
	
	@Column(name="firma_tutor")
	private boolean firmaTutor;
	
	@Column(name="firma_alumno")
	private boolean firmaAlumno;
	
	@Column(name="fecha")
	private String fecha;
	
	public Ficha() {
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Long getAlumnoId() {
		return alumnoId;
	}

	public boolean isFirmaProf() {
		return firmaProf;
	}

	public void setFirmaProf(boolean firmaProf) {
		this.firmaProf = firmaProf;
	}

	public boolean isFirmaTutor() {
		return firmaTutor;
	}

	public void setFirmaTutor(boolean firmaTutor) {
		this.firmaTutor = firmaTutor;
	}

	public boolean isFirmaAlumno() {
		return firmaAlumno;
	}

	public void setFirmaAlumno(boolean firmaAlumno) {
		this.firmaAlumno = firmaAlumno;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
