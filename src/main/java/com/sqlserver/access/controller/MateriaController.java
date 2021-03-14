package com.sqlserver.access.controller;

import com.sqlserver.access.model.Materia;
import com.sqlserver.access.repository.MateriaRepository;
import com.sqlserver.access.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("materia")
public class MateriaController {
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private MateriaService materiaService;

    @PostMapping("registro")
    String save (@Valid Materia materia, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "RegistroMaterias";
        }
       materiaRepository.save(materia);
        return "redirect:/materia/lista";
    }

    @PostMapping("actualizar")
    String update (@RequestParam(name = "id",required = false)Long id,@Valid Materia materia, BindingResult bindingResult) {
        System.out.println(id);
        if (bindingResult.hasErrors()){
            return "RegistroMaterias";
        }
        materia.setMateriaId(id);
        materiaRepository.save(materia);
        return "redirect:/materia/lista";
    }
    @PostMapping("borrar/{id}")
    String delete(@PathVariable("id") Long id){
        System.out.println(id);
        materiaRepository.deleteById(id);
        return "redirect:/materia/lista";

    }

    @GetMapping
    Iterable<Materia> listaMaterias(){
        return materiaRepository.findAll();
    }

    @GetMapping("lista")
    String viewLista(Model model) {
        model.addAttribute("materias",materiaService.getAllMaterias());
        return "Materia";
    }

    @GetMapping("registro")
    String viewRegistro(@RequestParam(name = "id",required = false)Long id, Model model) {

        Materia materia;
        Boolean error = false;
        String titleMain = "Registro Materia";
        String btn1Title = "Crear nueva materia";
        Boolean modeUpdate = false;
        String pathForm = "/materia/registro";
        String namePage = "RegistroMaterias";
        if(id != null){
            System.out.println("Modo actualizar");
            titleMain = "Actualizar Materia";
            btn1Title = titleMain;
            modeUpdate = true;
            pathForm = "/materia/actualizar?id="+ id.toString();
            if (materiaRepository.existsById(id)){
                materia = materiaRepository.findById(id).get();
            }
            else{
                error = true;
                materia = new Materia();
            }

        }
        else {
            materia = new Materia();
        }


        model.addAttribute("materia",materia);
        model.addAttribute("existeMateria",error);
        model.addAttribute("titleMain",titleMain);
        model.addAttribute("btn1",btn1Title);
        model.addAttribute("modeUpdate",modeUpdate);
        model.addAttribute("pathAction",pathForm);

        return namePage;
    }


}
