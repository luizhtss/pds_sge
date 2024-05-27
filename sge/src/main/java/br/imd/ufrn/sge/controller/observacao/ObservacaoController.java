package br.imd.ufrn.sge.controller.observacao;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.discente.ObservacaoDiscente;
import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.service.ObservacaoDiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/observacoes")
public class ObservacaoController {

    @Autowired
    private ObservacaoDiscenteService observacaoDiscenteService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarObservacao(@RequestBody ObservacaoDiscente observacaoDiscente) {
        ObservacaoDiscente novaObservacao = observacaoDiscenteService.salvarObservacao(observacaoDiscente);
        return ResponseEntity.ok().body(novaObservacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarObservacao(@PathVariable Long id, @RequestBody ObservacaoDiscente observacaoDiscente) {
        Optional<ObservacaoDiscente> observacaoExistente = observacaoDiscenteService.encontrarPorId(id);
        if (observacaoExistente.isPresent()) {
            ObservacaoDiscente observacao = observacaoExistente.get();
            if(observacaoDiscente.getObservacao() != null)
                observacao.setObservacao(observacaoDiscente.getObservacao());
            ObservacaoDiscente observacaoAtualizada = observacaoDiscenteService.salvarObservacao(observacao);
            return ResponseEntity.ok().body(observacaoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar/{idMatriculaDiscente}")
    public ResponseEntity<?> listarObservacoesPorMatriculaDiscente(@PathVariable Long idMatriculaDiscente) {
        List<ObservacaoDiscente> obsEncontradas = observacaoDiscenteService.encontrarPorMatriculaDiscente(idMatriculaDiscente);

        if (!obsEncontradas.isEmpty()) {
            return ResponseEntity.ok().body(obsEncontradas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno com a matrícula " + idMatriculaDiscente + " não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarObservacao(@PathVariable Long id) {
        Optional<ObservacaoDiscente> observacaoExistente = observacaoDiscenteService.encontrarPorId(id);
        if (observacaoExistente.isPresent()) {
            observacaoDiscenteService.deletarObservacao(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
