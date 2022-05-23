/**
 * [Encargado de dar respuesta a cada una de las peticiones del cliente, la ruta
 * raiz es= 'http://localhost:4200']
 *
 * @author [Juan Carlos Vanegas Palencia]
 *
 */

package com.sofka.controller;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.sofka.domain.Persona;
import com.sofka.service.PersonaService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@RestController
@CrossOrigin(
        "http://localhost:4200"
)

public class PersonaController {

    @Autowired
    private PersonaService personaService;

    private Response response = new Response();

    /**
     * index o pagina inical donde nos saldra un breve mensaje diciendo que el backend
     * funciona perfectamente.
     * @return
     */
    @GetMapping(path = "/")
    public Map<String, String> index() {
        var respuesta = new HashMap<String, String>();
        respuesta.put("message", "Progranma Backend funcional");
        return respuesta;
    }

    /**
     * ruta en al cual podes ingresar desde el navegador y visualizar todos los datos
     * que tengas guardados en la bd
     * localhost:9090/personas
     * @return un jSon con los datos de la bd
     */
    @GetMapping(path = "/personas")
    public ResponseEntity<Response> listado() {
        try {
            response.data = personaService.list();
        } catch (Exception exc) {
            response.error = true;
            response.message = exc.getMessage();

        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * aqui estaremos relizando lo qu es la creacion de los nuevos contactos
     * @param persona
     * @return
     */
    @PostMapping(path = "/persona", consumes = "application/json")
    public ResponseEntity<Response> crear(@RequestBody Persona persona) {

        response.restart();
        try {
            log.info("persona creada: {}", persona);
            personaService.save(persona);
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        } catch (Exception exc) {
            response.status = exc.getCause().toString();
            response.error = true;
            return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
        }
    }

    /**
     * metodo que usaremos para realizar la eliminacion de las personas o mas bien de los
     * contactos que hemos guardado en la bd
     * @param persona
     * @return
     */
    @DeleteMapping(path = "/persona/{id}")
    public ResponseEntity<Persona> borrar(Persona persona) {
        log.info("Borrar persona: {}", persona);
        personaService.delete(persona);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    /**
     * metodo que estaremos usando para realizar la actualizacion de los datos de cada contacto quee tengamos guardos
     * en la base de datos
     * @param persona
     * @param id
     * @return
     */
    @PutMapping(path = "/persona/{id}")
    public ResponseEntity<Persona> actualizar(Persona persona, @PathVariable("id") Long id) {
        log.info("Moficar persona: {}", persona);
        personaService.update(id, persona);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }



}
