
/**
 * [Implementa los métodos para asegurar un CRUD con la clase PersonaService]
 *
 * @author [Juan Carlos Vanegas Palencia]
 *
 * @version [1.0.0 / 2022-03-13]
 */




package com.sofka.service;

import com.sofka.dao.PersonaDao;
import com.sofka.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IPersonaService {

    @Autowired // para inyectar UsuarioDao
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> list() {
        List<Persona> personas = null;
        try {
            personas = (List<Persona>) personaDao.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return personas;
    }

    @Override
    @Transactional
    public Persona save(Persona persona) {
        return personaDao.save(persona);
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona persona) {
        persona.setId(id);
        return personaDao.save(persona);
    }



    @Override
    @Transactional
    public void delete(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findUser(Persona persona) {
        return personaDao.findById(persona.getId());
    }
}
