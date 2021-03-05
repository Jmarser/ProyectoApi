package com.jmarser.api.service;

import java.util.List;
import java.util.Optional;

/*Interfaz genérica que contiene todos los métodos básicos de un crud*/
public interface BaseService<E> {
	
	public List<E> findAll();//lista todos los objetos
	
	public Optional<E> findById(Long id);//obtiene un objeto por su id
	
	public E findByEmail(String email);//obtiene un objeto por su email
	
	public E save(E entidad);//guarda un objeto en la base de datos
	
	public E update(Long id, E entidad);//actualiza un objeto de la base de datos
	
	public void deleteById(Long id);//elimina un objeto de la base de datos.

}
