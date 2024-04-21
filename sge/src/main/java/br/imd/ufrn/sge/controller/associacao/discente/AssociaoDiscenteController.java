package br.imd.ufrn.sge.controller.associacao.discente;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associacao/discente")
public class AssociaoDiscenteController {



    @PostMapping("/turma")
    public void associarDiscenteTurma(@PathVariable Long idDiscente, @PathVariable Long idTurma) {

    }

}
