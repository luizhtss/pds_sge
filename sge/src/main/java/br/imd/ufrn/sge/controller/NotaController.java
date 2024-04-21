package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/discente", produces="application/json")
public class NotaController {

        @Autowired
        private NotaService notaService;

        @GetMapping
        public List<DiscenteMateria> listarNotas() {
            return notaService.listarTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> obterNotasPorId(@PathVariable Long id) {
            Optional<DiscenteMateria> nota = notaService.encontrarPorId(id);

            if (nota.isPresent()){
                return ResponseEntity.ok().body(nota.get());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de Notas com o ID " + id + " não encontrado");
            }
        }
        @GetMapping("/discente/{id}")
        public ResponseEntity<?> obterNotasPorIdDiscente(@RequestParam Long id) {
            List<DiscenteMateria> notasEncontradas = notaService.encontrarPorIdDiscente(id);

            if (!notasEncontradas.isEmpty()) {
                return ResponseEntity.ok().body(notasEncontradas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com o ID" + id + " não encontrados");
            }
        }

        @GetMapping("/materia/{id}")
        public ResponseEntity<?> obterNotasPorIdMateria(@RequestParam Long id) {
            List<DiscenteMateria> notasEncontradas = notaService.encontrarPorIdMateria(id);

            if (!notasEncontradas.isEmpty()) {
                return ResponseEntity.ok().body(notasEncontradas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria com o ID" + id + " não encontrada");
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<DiscenteMateria> atualizarNota(@PathVariable Long id, @RequestBody DiscenteMateria nota) {
            Optional<DiscenteMateria> notaExistente = notaService.encontrarPorId(id);
            if (notaExistente.isPresent()) {
                DiscenteMateria discenteMateria = notaExistente.get();
                discenteMateria.setUnidade1(nota.getUnidade1());
                discenteMateria.setUnidade1(nota.getUnidade2());
                discenteMateria.setUnidade1(nota.getUnidade3());
                DiscenteMateria notaAtualizada = notaService.salvar(discenteMateria);
                return ResponseEntity.ok().body(notaAtualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

}
