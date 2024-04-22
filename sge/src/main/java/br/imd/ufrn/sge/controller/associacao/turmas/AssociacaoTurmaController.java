package br.imd.ufrn.sge.controller.associacao.turmas;

import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.service.MateriaService;
import br.imd.ufrn.sge.service.associacao.AssociacaoMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associacao/turma")
public class AssociacaoTurmaController {

    @Autowired
    AssociacaoMateriaService associacaoMateriaService;

    @PutMapping("/materia/{idTurma}/{idMateria}")
    public ResponseEntity<?> associarTurmaMateria(@PathVariable Long idTurma, @PathVariable Long idMateria) {
        try {
            Turma turma = associacaoMateriaService.associarMateriaTurma(idMateria, idTurma);
            return ResponseEntity.ok().body(turma);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
