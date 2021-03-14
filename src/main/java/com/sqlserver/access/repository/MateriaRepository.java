package com.sqlserver.access.repository;

import com.sqlserver.access.model.Materia;
import com.sqlserver.access.model.MateriasProfesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MateriaRepository extends CrudRepository<Materia, Long> {
    @Modifying
    @Query("UPDATE Materia  m set m.materiaNombre = ?2  WHERE m.materiaId = ?1")
    void upDateMateria(Long id, String nombre);
}
