package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.turma.Turma;
import br.imd.ufrn.sge.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path="/api/turmas", produces="application/json")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping("/criar")
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma t) {
        Turma novaTurma = turmaService.salvar(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }
}
