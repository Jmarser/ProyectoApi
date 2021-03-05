package com.jmarser.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmarser.api.entity.Ficha;

@Repository
public interface FichaRepository extends CrudRepository<Ficha, Long>{

	public List<Ficha> findByAlumnoId(Long id);
}
