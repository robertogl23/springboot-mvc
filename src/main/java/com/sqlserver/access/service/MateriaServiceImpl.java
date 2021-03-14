package com.sqlserver.access.service;

import com.sqlserver.access.model.Materia;
import com.sqlserver.access.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;
    @Override
    public Iterable<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }
}
