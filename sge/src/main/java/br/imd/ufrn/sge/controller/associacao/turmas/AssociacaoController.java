package br.imd.ufrn.sge.controller.associacao.turmas;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associacao/turma-materia")
public class AssociacaoController {


    @PutMapping
    public void associarTurmaMateria(@RequestParam Long idTurma, @RequestParam Long idMateria) {

    }

}
