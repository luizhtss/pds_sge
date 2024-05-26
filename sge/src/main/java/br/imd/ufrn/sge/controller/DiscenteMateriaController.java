package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.service.DiscenteMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/discente-materia", produces="application/json")
public class DiscenteMateriaController {

        @Autowired
        private DiscenteMateriaService disMatService;

        @GetMapping("/notas")
        public List<DiscenteMateria> listarNotas() {
            return disMatService.listarTodos();
        }


        @GetMapping("/notas/{id}")
        public ResponseEntity<?> obterNotasPorId(@PathVariable Long id) {
            Optional<DiscenteMateria> nota = disMatService.encontrarPorId(id);

            if (nota.isPresent()){
                return ResponseEntity.ok().body(nota.get());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de Notas com o ID " + id + " não encontrado");
            }
        }
        @GetMapping("/notas/discente/{matricula_discente}")
        public ResponseEntity<?> obterNotasPorMatriculaDiscente(@PathVariable Long matricula_discente) {
            List<DiscenteMateria> notasEncontradas = disMatService.encontrarPorMatriculaDiscente(matricula_discente);

            if (!notasEncontradas.isEmpty()) {
                return ResponseEntity.ok().body(notasEncontradas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com a matrícula " + matricula_discente + " não encontrado");
            }
        }

        @GetMapping("/notas/materia/{id}")
        public ResponseEntity<?> obterNotasPorIdMateria(@PathVariable Long id) {
            List<DiscenteMateria> notasEncontradas = disMatService.encontrarPorIdMateria(id);

            if (!notasEncontradas.isEmpty()) {
                return ResponseEntity.ok().body(notasEncontradas);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria com o ID " + id + " não encontrada");
            }
        }

        @PutMapping("/notas/{id}")
        public ResponseEntity<DiscenteMateria> atualizarNota(@PathVariable Long id, @RequestBody DiscenteMateria disMat) {
            Optional<DiscenteMateria> disMatExistente = disMatService.encontrarPorId(id);
            if (disMatExistente.isPresent()) {
                DiscenteMateria discenteMateria = disMatExistente.get();
                if(disMat.getUnidade1() != null)
                    discenteMateria.setUnidade1(disMat.getUnidade1());
                if(disMat.getUnidade2() != null)
                    discenteMateria.setUnidade2(disMat.getUnidade2());
                if(disMat.getUnidade3() != null)
                    discenteMateria.setUnidade3(disMat.getUnidade3());
                DiscenteMateria notaAtualizada = disMatService.salvar(discenteMateria);
                return ResponseEntity.ok().body(notaAtualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @PutMapping("/frequencia/{id}")
    public ResponseEntity<DiscenteMateria> atualizarFrequencia(@PathVariable Long id, @RequestBody DiscenteMateria disMat) {
        Optional<DiscenteMateria> disMatExistente = disMatService.encontrarPorId(id);
        if (disMatExistente.isPresent()) {
            DiscenteMateria discenteMateria = disMatExistente.get();
            if(disMat.getPresenca() != null)
                discenteMateria.setPresenca(disMat.getPresenca());
            DiscenteMateria disMatAtualizada= disMatService.salvar(discenteMateria);
            return ResponseEntity.ok().body(disMatAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
