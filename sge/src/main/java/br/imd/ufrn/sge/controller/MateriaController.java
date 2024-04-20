package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.service.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/materias", produces="application/json")
public class MateriaController {

    @Autowired
    private Materia materia;

}
