package br.imd.ufrn.sge.controller.associacao.discente;


import br.imd.ufrn.sge.service.associacao.matriculadiscente.AssociacaoMatriculaDiscente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associacao/matricula")
public class AssociaoMatriculaController {

    @Autowired
    AssociacaoMatriculaDiscente associacaoMatriculaDiscente;

    @PutMapping("/turma/{idMatriculaDiscente}/{idTurma}")
    public void associarDiscenteTurma(@PathVariable Long idMatriculaDiscente, @PathVariable Long idTurma) {
        try{
            associacaoMatriculaDiscente.associarMatriculaDiscenteEmTurma(idMatriculaDiscente, idTurma);
            ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            ResponseEntity.status(400).body(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
