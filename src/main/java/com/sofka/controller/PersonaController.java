/**
 * [Encargado de dar respuesta a cada una de las peticiones del cliente, la ruta
 * raiz es= 'http://localhost:4200']
 *
 * @author [Juan Carlos Vanegas Palencia]
 *
 * @version [1.0.0 / 2022-03-13]
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

    @GetMapping(path = "/")
    public Map<String, String> index() {
        var respuesta = new HashMap<String, String>();
        respuesta.put("message", "Hola Mundo");
        return respuesta;
    }

    @GetMapping(path = "/personas")
    public Response listado() {
        try {
            response.data = personaService.list();
        } catch (Exception exc) {
            response.error = true;
            response.message = exc.getMessage();

        }
        return response;
    }

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

    @DeleteMapping(path = "/persona/{id}")
    public ResponseEntity<Persona> borrar(Persona persona) {
        log.info("Borrar persona: {}", persona);
        personaService.delete(persona);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PutMapping(path = "/persona/{id}")
    public ResponseEntity<Persona> actualizar(Persona persona, @PathVariable("id") Long id) {
        log.info("Moficar persona: {}", persona);
        personaService.update(id, persona);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }



}
