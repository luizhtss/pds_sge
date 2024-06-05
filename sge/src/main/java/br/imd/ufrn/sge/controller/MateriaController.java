package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.materia.Materia;
import br.imd.ufrn.sge.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/materias", produces="application/json")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> listarMaterias() {
        return materiaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterMateriaPorId(@PathVariable Long id) {
        Optional<Materia> materia= materiaService.encontrarPorId(id);

        if (materia.isPresent()){
            return ResponseEntity.ok().body(materia.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia com o ID " + id + " não encontrada");
        }
    }

    @GetMapping("/docente/{id}")
    public ResponseEntity<?> obterMateriaPorDocente (@PathVariable Long id) {
        Optional<Materia> materia= materiaService.encontrarPorDocente(id);

        if (materia.isPresent()){
            return ResponseEntity.ok().body(materia.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontradas nenhuma matétia par o docente de id " + id);
        }
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<?> obterMateriaPorTurma (@PathVariable Long id) {
        Optional<Materia> materia= materiaService.encontrarPorTurma(id);

        if (materia.isPresent()){
            return ResponseEntity.ok().body(materia.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontradas nenhuma matétia para a turma de id " + id);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Materia> criarMateria(@RequestBody Materia materia) {
        Materia novaMateria = materiaService.salvar(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMateria);
    }
    //cria uma materia e liga ao docente por meio do id
    @PostMapping("/criar/{id}")
    public ResponseEntity<Materia> criarMateriaDocenteComId(@PathVariable Long id, @RequestBody Materia materia) {
        Materia novaMateria = materiaService.salvar(id,materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMateria);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMateria(@PathVariable Long id) {
        Optional<Materia> materiaExistente = materiaService.encontrarPorId(id);
            materiaService.deletar(id);
            return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable Long id, @RequestBody Materia materia) {
        Optional<Materia> materiaExistente = materiaService.encontrarPorId(id);
        if (materiaExistente.isPresent()) {
            Materia dadosMateria = materiaExistente.get();
            if(materia.getNome() != null)
                dadosMateria.setNome(materia.getNome());
            if(materia.getDescricao() != null)
                dadosMateria.setDescricao(materia.getDescricao());
            Materia materiaAtualizada = materiaService.salvar(dadosMateria);
            return ResponseEntity.ok().body(materiaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
