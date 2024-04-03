package br.imd.ufrn.sge.relatorio.controller;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.providers.LLMAProvider;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioAcademico;
import br.imd.ufrn.sge.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/relatorio", produces="application/json")
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    LLMAProvider llmaProvider;

    @PostMapping("/academico")
    ResponseEntity<RelatorioAcademico> getRelatorioAcademico(@RequestBody Long id_matricula_discente) {
        RelatorioAcademico rel = (RelatorioAcademico) relatorioService.obterRelatorioAcademico(llmaProvider, new MatriculaDiscente());
        return ResponseEntity.ok().body(rel);
    }

}
