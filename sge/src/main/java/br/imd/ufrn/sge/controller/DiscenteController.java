package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/discente", produces="application/json")
public class DiscenteController {
    @Autowired
    private DiscenteService discenteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obterDiscentePorId(@PathVariable Long id) {
        Optional<Discente> discentes = discenteService.encontrarDiscente(id);

        if (discentes.isPresent()){
            return ResponseEntity.ok().body(discentes.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discente com o ID " + id + " não encontrada");
        }
    }
    @PostMapping("/criar")
    public ResponseEntity<Discente> criarDiscente(@RequestBody Discente discentes) {
        Discente novoDiscente = discenteService.salvarDiscente(discentes);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDiscente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiscente(@PathVariable Long id) {
        Optional<Discente> discenteExistente = discenteService.encontrarDiscente(id);
        if (discenteExistente.isPresent()) {
            discenteService.deletarDiscente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }






}