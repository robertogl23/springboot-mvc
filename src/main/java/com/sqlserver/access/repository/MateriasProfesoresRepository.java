package com.sqlserver.access.repository;

import com.sqlserver.access.model.MateriasProfesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriasProfesoresRepository extends JpaRepository<MateriasProfesores, Long> {
    @Query("SELECT mp FROM MateriasProfesores mp WHERE mp.materia.materiaId = ?1")
    List<MateriasProfesores> findAllByMateria(Long materiaId);

    @Query("SELECT mp FROM MateriasProfesores mp WHERE mp.profesor.profesorId = ?1")
    List<MateriasProfesores> findAllByProfesor(Long profesorId);
}
