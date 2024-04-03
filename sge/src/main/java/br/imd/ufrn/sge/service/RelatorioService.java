package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.data.DadosAcademicoFetcher;
import br.imd.ufrn.sge.relatorio.data.DadosObservacaoFetcher;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    DadosAcademicoFetcher dadosAcademicoFetcher;

    @Autowired
    DadosObservacaoFetcher dadosObservacaoFetcher;

    public Relatorio obterRelatorioAcademico(ILLMProvider relatorioProvider, MatriculaDiscente matriculaDiscente){
        String data = dadosAcademicoFetcher.fetchData(matriculaDiscente);
        return relatorioProvider.gerarRelatorioBaseAcademico(data);
    }

    public Relatorio obterRelatorioPessoal(ILLMProvider relatorioProvider, MatriculaDiscente matriculaDiscente){
        String data = dadosAcademicoFetcher.fetchData(matriculaDiscente);
        return relatorioProvider.gerarRelatorioBasePessoal(data);
    }


}
