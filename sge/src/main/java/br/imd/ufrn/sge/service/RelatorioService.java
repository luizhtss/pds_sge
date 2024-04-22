package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.data.DadosAcademicoFetcher;
import br.imd.ufrn.sge.relatorio.data.DadosObservacaoFetcher;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    DadosAcademicoFetcher dadosAcademicoFetcher;

    @Autowired
    DadosObservacaoFetcher dadosObservacaoFetcher;

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    @Autowired
    NotaService notaService;

    public Relatorio obterRelatorioAcademico(ILLMProvider relatorioProvider, Long idMatriculaDiscente) throws IOException, InterruptedException, IllegalArgumentException {
        Optional<MatriculaDiscente> matriculaDiscenteDB = matriculaDiscenteService.obterMatriculaDiscente(idMatriculaDiscente);
        if (matriculaDiscenteDB.isPresent()) {
            if (notaService.todasUnidadesPreenchidas(idMatriculaDiscente)) {
                String data = dadosAcademicoFetcher.fetchData(matriculaDiscenteDB.get());
                return relatorioProvider.gerarRelatorioBaseAcademico(data);
            } else {
                throw new IllegalArgumentException("Impossível gerar relatório acadêmico, nem todas as unidades estão preenchidas.");
            }

        }else{
            throw new IllegalArgumentException("Matricula não encontrada");
        }
    }

    public Relatorio obterRelatorioPessoal(ILLMProvider relatorioProvider, Long idMatriculaEstudante) throws IOException, InterruptedException {
       /* String data = dadosObservacaoFetcher.fetchData(idMatriculaEstudante);
        return relatorioProvider.gerarRelatorioBasePessoal(data);*/
        return null;
    }


}
