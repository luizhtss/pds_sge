package br.imd.ufrn.sge.controller.associacao.pessoa;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.service.associacao.pessoa.AssociacaoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associacao/pessoa")
public class AssosiacaoPessoaController {

    @Autowired
    AssociacaoPessoaService associacaoPessoaService;


    @PostMapping("/discente/{idPessoa}")
    public void associarPessoaDiscente(@PathVariable Long idPessoa) {
        try{
            MatriculaDiscente matriculaDiscente = associacaoPessoaService.associarPessoaDiscente(idPessoa);
            ResponseEntity.ok().body(matriculaDiscente);
        }catch (IllegalArgumentException e){
            ResponseEntity.status(400).body(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
