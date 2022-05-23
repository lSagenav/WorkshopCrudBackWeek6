/**
 * [Contiene contiene los Querys para las consultas en la base de datos MYSQL de
 * la Interfaz Persona]
 *
 * @author [Juan Carlos Vanegas Palencia]
 *
 */


package com.sofka.dao;

import com.sofka.domain.Persona;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonaDao extends CrudRepository<Persona, Long> {




}
