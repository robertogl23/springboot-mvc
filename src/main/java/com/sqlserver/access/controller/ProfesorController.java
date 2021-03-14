package com.sqlserver.access.controller;

import com.sqlserver.access.model.Materia;
import com.sqlserver.access.model.MateriasProfesores;
import com.sqlserver.access.model.PageAttributesProfesor;
import com.sqlserver.access.model.Profesor;
import com.sqlserver.access.repository.MateriaRepository;
import com.sqlserver.access.repository.MateriasProfesoresRepository;
import com.sqlserver.access.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("profesor")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriasProfesoresRepository materiasProfesoresRepository;

    @PostMapping
    Profesor save(@RequestBody Profesor profesor){
        return profesorRepository.save(profesor);
    }

    @GetMapping
    List<Profesor> listaProfesores() {
        return profesorRepository.findAll();
    }

    @GetMapping("materias/{id}")
    List<MateriasProfesores> listaMaterias(@PathVariable("id") Long id) {
        return materiasProfesoresRepository.findAllByMateria(id);
    }
    @GetMapping("profesores/{id}")
    List<MateriasProfesores> listaMProfesores(@PathVariable("id") Long id) {
        return materiasProfesoresRepository.findAllByProfesor(id);
    }

    @PostMapping("save")
    MateriasProfesores saveMP(@RequestBody IdsPM idsPM){
        System.out.println(idsPM.getProfesorId());
        Optional<Profesor> p = profesorRepository.findById(idsPM.getProfesorId());
        Optional<Materia> m = materiaRepository.findById(idsPM.getMateriaId());
        MateriasProfesores materiasProfesores = new MateriasProfesores();
        materiasProfesores.setMateria(m.get());
        materiasProfesores.setProfesor(p.get());

        return materiasProfesoresRepository.save(materiasProfesores);

    }

    @PostMapping("registro")
    String registroProfesor(@Valid Profesor profesor, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "RegistroMaestros";
        }
        System.out.println(profesor.getApellidoM());
        return "redirect:/profesor/registro";
    }
    @GetMapping("registro")
    String registroMastro(@RequestParam(name = "id",required = false)Long id, Model model){
        model.addAttribute("profesor",new Profesor());
        return "RegistroMaestros";
    }
}
