package br.imd.ufrn.sge.controller.associacao;


import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.service.MatriculaDiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/matricula")
public class MatriculaDiscenteController {

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obterMatriculaPorId(@PathVariable Long id) {
        Optional<MatriculaDiscente> matricula = matriculaDiscenteService.findById(id);

        if (!matricula.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(matricula.get());
    }

    @GetMapping("/discente/{matricula_discente}")
    public ResponseEntity<?> obterMatriculaPorMatriculaDiscente(@PathVariable String matricula_discente) {
        Optional<MatriculaDiscente> matricula = matriculaDiscenteService.encontrarMatriculaDiscentePorNumeroMatricula(matricula_discente);

        if (!matricula.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(matricula.get());
    }

}
