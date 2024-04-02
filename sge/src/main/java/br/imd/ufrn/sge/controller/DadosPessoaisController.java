package br.imd.ufrn.sge.controller;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.service.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/pessoas", produces="application/json")
public class DadosPessoaisController {

    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @GetMapping
    public List<DadosPessoais> listarPessoas() {
        return dadosPessoaisService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPessoaPorId(@PathVariable Long id) {
        Optional<DadosPessoais> pessoa = dadosPessoaisService.encontrarPorId(id);

        if (pessoa.isPresent()){
            return ResponseEntity.ok().body(pessoa.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa com o ID " + id + " não encontrada");
        }
    }

    @PostMapping
    public ResponseEntity<DadosPessoais> criarPessoa(@RequestBody DadosPessoais pessoa) {
        DadosPessoais novaPessoa = dadosPessoaisService.salvar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosPessoais> atualizarPessoa(@PathVariable Long id, @RequestBody DadosPessoais pessoa) {
        Optional<DadosPessoais> pessoaExistente = dadosPessoaisService.encontrarPorId(id);
        if (pessoaExistente.isPresent()) {
            pessoa.setId(id);
            DadosPessoais pessoaAtualizada = dadosPessoaisService.salvar(pessoa);
            return ResponseEntity.ok().body(pessoaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        Optional<DadosPessoais> pessoaExistente = dadosPessoaisService.encontrarPorId(id);
        if (pessoaExistente.isPresent()) {
            dadosPessoaisService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<DadosPessoais>> buscarPessoasPeloAnoDeCriacao(@PathVariable int ano) {
        List<DadosPessoais> pessoas = dadosPessoaisService.buscarDadosPeloAnoDeCriacao(ano);
        return ResponseEntity.ok().body(pessoas);
    }
}
