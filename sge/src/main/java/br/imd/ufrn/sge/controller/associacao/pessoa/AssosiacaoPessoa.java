package br.imd.ufrn.sge.controller.associacao.pessoa;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associacao/pessoa")
public class AssosiacaoPessoa {

    @PostMapping("/discente")
    public void associarPessoaDiscente(@PathVariable Long idPessoa) {

    }
}
