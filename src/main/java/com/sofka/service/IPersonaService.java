/**
 * [Contiene los métodos para asegurar un CRUD con la Interfaz IPersonaService]
 *
 * @author [Juan Carlos Vanegas Palencia]
 *
 */


package com.sofka.service;

import com.sofka.domain.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    public List<Persona> list();

    public Persona save(Persona persona);

    public Persona update(Long id, Persona persona);

    public void delete(Persona persona);

    public Optional<Persona> findUser(Persona persona);

}
