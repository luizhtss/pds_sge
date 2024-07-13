package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.config.GlobalStrategy;
import br.imd.ufrn.sge.framework.notas.INotaStrategy;
import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.Frequencia;
import br.imd.ufrn.sge.framework.aprovacao_materia.*;
import br.imd.ufrn.sge.service.DiscenteMateriaService;
import br.imd.ufrn.sge.service.FrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/discente-materia", produces="application/json")
public class DiscenteMateriaController {

    @Autowired
    private GlobalStrategy globalStrategy;

    @Autowired
    private DiscenteMateriaService disMatService;

    @Autowired
    private FrequenciaService frequenciaService;

    @GetMapping("/")
        public List<DiscenteMateria> listarNotas() {
            return disMatService.listarTodos();
        }


    @GetMapping("/{id}")
    public ResponseEntity<?> obterDiscenteMateriaPorId(@PathVariable Long id) {
        Optional<DiscenteMateria> nota = disMatService.encontrarPorId(id);

        if (nota.isPresent()){
            return ResponseEntity.ok().body(nota.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de Notas com o ID " + id + " não encontrado");
        }
    }
    @GetMapping("/discente/{matricula_discente}")
    public ResponseEntity<?> obterMateriaPorMatriculaDiscente(@PathVariable String matricula_discente) {
        List<DiscenteMateria> notasEncontradas = disMatService.encontrarPorMatriculaDiscente(matricula_discente);

        if (!notasEncontradas.isEmpty()) {
            return ResponseEntity.ok().body(notasEncontradas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com a matrícula " + matricula_discente + " não encontrado");
        }
    }

    @GetMapping("/materia/{id}")
    public ResponseEntity<?> obterDiscentePorIdMateria(@PathVariable Long id) {
        List<DiscenteMateria> notasEncontradas = disMatService.encontrarPorIdMateria(id);

        if (!notasEncontradas.isEmpty()) {
            return ResponseEntity.ok().body(notasEncontradas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria com o ID " + id + " não encontrada");
        }
    }

    @PutMapping(value="/notas/{id}")
    public ResponseEntity<DiscenteMateria> atualizarNotas(
            @PathVariable Long id,
            @RequestBody DiscenteMateria disMat
    ){
            Optional<DiscenteMateria> disMatExistente = disMatService.encontrarPorId(id);
        if (disMatExistente.isPresent()) {
            DiscenteMateria discenteMateria = disMatExistente.get();
            DiscenteMateria notaAtualizada = disMatService.salvar(discenteMateria,disMat);
            return ResponseEntity.ok().body(notaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/frequencia/edit/{id}")
    public ResponseEntity<?> atualizarFrequencia(@PathVariable Long id, @RequestBody Frequencia frequencia) {
        Optional<DiscenteMateria> disMatExistenteOpt = disMatService.encontrarPorId(id);
        if (disMatExistenteOpt.isPresent()) {
            DiscenteMateria discenteMateria = disMatExistenteOpt.get();
            frequencia.setDiscenteMateria(discenteMateria);

            Frequencia savedFrequencia = frequenciaService.salvar(frequencia);
            if (savedFrequencia != null) {
                return ResponseEntity.ok().body(discenteMateria.getFrequencias());
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Frequencia already exists.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/frequencia/{id}")
    public ResponseEntity<?> adicionarFrequencia(@PathVariable Long id, @RequestBody Frequencia frequencia) {
        Optional<DiscenteMateria> disMatExistenteOpt = disMatService.encontrarPorId(id);
        if (disMatExistenteOpt.isPresent()) {
            DiscenteMateria discenteMateria = disMatExistenteOpt.get();
            frequencia.setDiscenteMateria(discenteMateria);

            Frequencia savedFrequencia = frequenciaService.salvar(frequencia);
            if (savedFrequencia != null) {
                return ResponseEntity.ok().body(discenteMateria.getFrequencias());
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Frequencia already exists.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/frequencia/{discenteMateriaId}")
    public ResponseEntity<?> obterFrequenciasPorDiscenteMateriaId(@PathVariable Long discenteMateriaId) {
        Optional<DiscenteMateria> discenteMateriaOpt = disMatService.encontrarPorId(discenteMateriaId);
        if (discenteMateriaOpt.isPresent()) {
            List<Frequencia> frequencias = discenteMateriaOpt.get().getFrequencias();
            return ResponseEntity.ok().body(frequencias);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DiscenteMateria with ID " + discenteMateriaId + " not found");
        }
    }

    @GetMapping("/calcular-nota/{id}")
    public ResponseEntity<?> calcularNota(@PathVariable Long id) {
        Optional<DiscenteMateria> disMatExistente = disMatService.encontrarPorId(id);
        if (disMatExistente.isPresent()) {
            DiscenteMateria discenteMateria = disMatExistente.get();
            try {
                float notaCalculada = disMatService.calcularNota(discenteMateria.getUnidade1(),discenteMateria.getUnidade2(),discenteMateria.getUnidade3());
                return ResponseEntity.ok().body(notaCalculada);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
