package com.sqlserver.access.model;

import com.sqlserver.access.model.Materia;
import com.sqlserver.access.model.Profesor;

import javax.persistence.*;
@Entity
public class MateriasProfesores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mpId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "materia_id",nullable = false)
    private Materia materia;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "profesor_id",nullable = false)
    private Profesor profesor;

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
