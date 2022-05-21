/**
 * [Clase en la cual se crean las variables unidas a las variables que se usaran en la base de datos]
 *
 * @author [Juan Carlos Vanegas Palencia]
 *
 * @version [1.0.0 / 2022-03-13]
 */




package com.sofka.domain;

import lombok.Data;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Long id;

    @Column(name = "full_name")
    private String nombre;

    @Column(name = "number_cell")
    private String numberCell;

    @Column(name = "e_mail")
    private String email;


    @Column(name = "data_born")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataBorn;



}
