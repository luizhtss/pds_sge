package br.imd.ufrn.sge.relatorio.controller;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.providers.LLMAProvider;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioAcademico;
import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioPessoal;
import br.imd.ufrn.sge.relatorio.repository.relatorios.RelatorioAcademicoRepository;
import br.imd.ufrn.sge.relatorio.repository.relatorios.RelatorioPessoalRepository;
import br.imd.ufrn.sge.relatorio.repository.relatorios.RelatorioRepository;
import br.imd.ufrn.sge.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path="/api/relatorio", produces="application/json")
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    LLMAProvider llmaProvider;

    @Autowired
    RelatorioAcademicoRepository relatorioAcademicoRepository;

    @Autowired
    RelatorioPessoalRepository relatorioPessoalRepository;

    @GetMapping("/academico")
    ResponseEntity<RelatorioAcademico> getRelatorioAcademico() {
        RelatorioAcademico rel = null;
        try {
            rel = (RelatorioAcademico) relatorioService.obterRelatorioAcademico(llmaProvider, new MatriculaDiscente());
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.internalServerError().build();
        }
        //relatorioAcademicoRepository.save(rel);
        return ResponseEntity.ok().body(rel);
    }

    @GetMapping("/pessoal")
    ResponseEntity<RelatorioPessoal> getRelatorioPessoal() {
        RelatorioPessoal rel = null;
        try {
            rel = (RelatorioPessoal) relatorioService.obterRelatorioPessoal(llmaProvider, new MatriculaDiscente());
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.internalServerError().build();
        }
        //relatorioPessoalRepository.save(rel);
        return ResponseEntity.ok().body(rel);
    }

}
