package br.imd.ufrn.sge.controller.observacao;

import br.imd.ufrn.sge.models.discente.ObservacaoDiscente;
import br.imd.ufrn.sge.service.ObservacaoDiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/editar")
    public ResponseEntity<?> editarObservacao(@RequestBody ObservacaoDiscente observacaoDiscente) {
        ObservacaoDiscente novaObservacao = null;
        try {
            novaObservacao = observacaoDiscenteService.editarObservacao(observacaoDiscente);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok().body(novaObservacao);
    }

    @GetMapping("/listar/{idMatriculaDiscente}")
    public ResponseEntity<?> listarObservacoesPorMatriculaDiscente(@PathVariable Long idMatriculaDiscente) {
        return ResponseEntity.ok().body(observacaoDiscenteService.encontrarPorMatriculaDiscente(idMatriculaDiscente));
    }

    @DeleteMapping
    public ResponseEntity<?> deletarObservacao(@RequestBody ObservacaoDiscente observacaoDiscente) {
        observacaoDiscenteService.deletarObservacao(observacaoDiscente);
        return ResponseEntity.ok().body("Observação deletada com sucesso");
    }

}
