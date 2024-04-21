package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/turmas", produces="application/json")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public List<Turma> listarTurma() {
        return turmaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterTurmaPorId(@PathVariable Long id) {
        Optional<Turma> turma = turmaService.encontrarPorId(id);

        if (turma.isPresent()){
            return ResponseEntity.ok().body(turma.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma com o ID " + id + " não encontrada");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Turma>> buscarTurmaPeloNome(@PathVariable String nome) {
        List<Turma> turmas = turmaService.findByName(nome);
        return ResponseEntity.ok().body(turmas);
    }

    @PostMapping("/criar")
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma t) {
        Turma novaTurma = turmaService.salvar(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma t) {
        Optional<Turma> turmaExistente = turmaService.encontrarPorId(id);
        if (turmaExistente.isPresent()) {
            Turma turma = turmaExistente.get();
            turma.setNome(t.getNome());
            Turma turmaAtualizada = turmaService.salvar(turma);
            return ResponseEntity.ok().body(turmaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        Optional<Turma> turmaExistente = turmaService.encontrarPorId(id);
        if (turmaExistente.isPresent()) {
            turmaService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
