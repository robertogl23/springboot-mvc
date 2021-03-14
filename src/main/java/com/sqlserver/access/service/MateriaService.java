package com.sqlserver.access.service;

import com.sqlserver.access.model.Materia;

import java.util.List;

public interface MateriaService {
    Iterable<Materia> getAllMaterias();
}
