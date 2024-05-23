package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.service.NotaService;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/notas", produces="application/json")
public class NotaController {

        @Autowired
        private NotaService notaService;

        @GetMapping
        public List<DiscenteMateria> listarNotas() {
            return notaService.listarTodos();
        }


        @GetMapping("/{id}")
        public ResponseEntity<?> obterNotasPorId(@PathVariable Long id) {
            try {
                Optional<DiscenteMateria> nota = notaService.encontrarPorId(id);
                return ResponseEntity.ok().body(nota.get());
            }
      catch (IllegalArgumentException e){
                e.printStackTrace();
                return ResponseEntity.status(400).body(e.getMessage());
            }
        }

        @GetMapping("/discente/{matricula_discente}")
        public ResponseEntity<?> obterNotasPorMatriculaDiscente(@PathVariable Long matricula_discente) {
            try {
            List<DiscenteMateria> notasEncontradas = notaService.encontrarPorMatriculaDiscente(matricula_discente);
            if (!notasEncontradas.isEmpty()) {
                return ResponseEntity.ok().body(notasEncontradas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com a matrícula " + matricula_discente + " não encontrado");
            }  }
            catch (IllegalArgumentException e){
                e.printStackTrace();
                return ResponseEntity.status(400).body(e.getMessage());
            }
        }

        @GetMapping("/materia/{id}")
        public ResponseEntity<?> obterNotasPorIdMateria(@PathVariable Long id) {

            List<DiscenteMateria> notasEncontradas = notaService.encontrarPorIdMateria(id);
            if (!notasEncontradas.isEmpty()) {
                return ResponseEntity.ok().body(notasEncontradas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria com o ID " + id + " não encontrada");
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<DiscenteMateria> atualizarNota(@PathVariable Long id, @RequestBody DiscenteMateria nota) {
            Optional<DiscenteMateria> notaExistente = notaService.encontrarPorId(id);
            if (notaExistente.isPresent()) {
                DiscenteMateria discenteMateria = notaExistente.get();
                if(nota.getUnidade1() != null)
                    discenteMateria.setUnidade1(nota.getUnidade1());
                if(nota.getUnidade2() != null)
                    discenteMateria.setUnidade2(nota.getUnidade2());
                if(nota.getUnidade3() != null)
                    discenteMateria.setUnidade3(nota.getUnidade3());
                DiscenteMateria notaAtualizada = notaService.salvar(discenteMateria);
                return ResponseEntity.ok().body(notaAtualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

}
