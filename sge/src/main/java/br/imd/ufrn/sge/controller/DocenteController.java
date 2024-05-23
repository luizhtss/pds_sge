package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.exceptions.IdJaExisteException;
import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/docentes", produces="application/json")
public class DocenteController {
    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public List<Docente> listarDocentes() {
        return docenteService.listarTodos();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Docente>> buscarDocentePeloNome(@PathVariable String nome) {
        List<Docente> docente = docenteService.findByName(nome);
        return ResponseEntity.ok().body(docente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterDocentePorId(@PathVariable Long id) {
        Optional<Docente> docente = docenteService.encontrarPorId(id);
        return ResponseEntity.ok().body(docente.get());
    }

    @PostMapping("/criar")
    public ResponseEntity<Docente> criarDocente(@RequestBody Docente docente) {
        Docente novoDocente = docenteService.salvar(docente);
        if (novoDocente != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoDocente);
        }
        throw new IdJaExisteException();
    }

    @DeleteMapping("/{id}")
    public void deletarDocente(@PathVariable Long id) {
        docenteService.deletar(id);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletarDocente(@PathVariable Long id) {
//        Optional<Docente> docenteExistente = docenteService.encontrarPorId(id);
//        if (docenteExistente.isPresent()) {
//            docenteService.deletar(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }




}
