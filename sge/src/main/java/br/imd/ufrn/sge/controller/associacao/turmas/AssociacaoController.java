package br.imd.ufrn.sge.controller.associacao.turmas;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/associacao/turma-materia")
public class AssociacaoController {

    @Autowired
    MateriaService materiaService;

    @PutMapping
    public void associarTurmaMateria(@RequestParam Long idTurma, @RequestParam Long idMateria) {

    }

}
