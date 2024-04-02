package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.models.relatorio.Relatorio;
import br.imd.ufrn.sge.relatorio.providers.DataProvider;
import br.imd.ufrn.sge.relatorio.providers.LLMAProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    LLMAProvider llmaProvider;

    @Autowired
    DataProvider dataProvider;

    public Relatorio obterRelatorio(MatriculaDiscente matriculaDiscente){
       Relatorio relatorio = llmaProvider.gerarRelatorioBase(dataProvider.fetchData(matriculaDiscente));
       return relatorio;
    }

}
