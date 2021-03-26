package com.jmarser.api.mapper;

import org.springframework.stereotype.Component;

import com.jmarser.api.entity.Alumno;
import com.jmarser.api.entity.Profesor;
import com.jmarser.api.entity.Tutor;
import com.jmarser.api.model.ModelAlumno;
import com.jmarser.api.model.ModelProfesor;
import com.jmarser.api.model.ModelTutor;

@Component("mapper")
public class Mapper {

	public static ModelAlumno convertirAlumno(Alumno alumno, ModelProfesor profesor, ModelTutor tutor) {
	
		ModelAlumno modelAlumno = new ModelAlumno(alumno, profesor, tutor);
		
		return modelAlumno;
	}
	
	public static ModelProfesor convertirProfesor(Profesor profesor) {
		ModelProfesor modelProfesor = new ModelProfesor(profesor);
		
		return modelProfesor;
	}
	
	public static ModelTutor convertirTutor(Tutor tutor) {
		
		ModelTutor modelTutor = new ModelTutor(tutor);
		
		return modelTutor;
	}
}
