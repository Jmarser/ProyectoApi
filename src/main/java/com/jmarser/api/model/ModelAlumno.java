package com.jmarser.api.model;

import java.util.ArrayList;
import java.util.List;
import com.jmarser.api.entity.Alumno;
import com.jmarser.api.entity.Ficha;


/*Clase con la que realizamos el mapeo de la clase alumno para enviar al cliente android sólo los datos que necesita.*/

public class ModelAlumno extends ModelBase{

	private List<Ficha> fichas;
	private ModelProfesor profesor;
	private ModelTutor tutor;
	private String ciclo;
	
	
	public ModelAlumno() {

	}

	public ModelAlumno(Alumno alumno, ModelProfesor profesor, ModelTutor tutor) {
		this.setId(alumno.getId());
		this.setNombre(alumno.getNombre());
		this.setPrimerApellido(alumno.getPrimerApellido());
		this.setSegundoApellido(alumno.getSegundoApellido());
		this.setEmail(alumno.getEmail());
		this.setCiclo(alumno.getCiclo());
		this.setFichas(alumno.getFichas());
		this.profesor = profesor;
		this.tutor = tutor;
	}
	
	public List<Ficha> getFichas() {
		return fichas;
	}
	
	/*Como cabe la posibilidad de que el alumno pueda cursos varios ciclos, necesitamos filtar las fichas sólo a las del curso que 
	 * este cursando actualmente*/
	public void setFichas(List<Ficha> fichas) {
		List<Ficha> aux = new ArrayList<Ficha>();
		for(int i = 0; i<fichas.size(); i++) {
			if(fichas.get(i).getCiclo().equals(this.getCiclo())) {
				aux.add(fichas.get(i));
			}
		}
		this.fichas = aux;
	}

	public ModelProfesor getProfesor() {
		return profesor;
	}

	public void setProfesor(ModelProfesor profesor) {
		this.profesor = profesor;
	}

	public ModelTutor getTutor() {
		return tutor;
	}

	public void setTutor(ModelTutor tutor) {
		this.tutor = tutor;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	

}
