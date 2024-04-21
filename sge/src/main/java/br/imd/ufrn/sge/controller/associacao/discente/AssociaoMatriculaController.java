package br.imd.ufrn.sge.controller.associacao.discente;


import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.service.associacao.AssociacaoMatriculaDiscente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associacao/matricula")
public class AssociaoMatriculaController {

    @Autowired
    AssociacaoMatriculaDiscente associacaoMatriculaDiscente;

    @PutMapping("/turma/{idMatriculaDiscente}/{idTurma}")
    public ResponseEntity<?> associarDiscenteTurma(@PathVariable Long idMatriculaDiscente, @PathVariable Long idTurma) {
        try{
            Turma turma = associacaoMatriculaDiscente.associarMatriculaDiscenteEmTurma(idMatriculaDiscente, idTurma);
            return ResponseEntity.ok().body(turma);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
