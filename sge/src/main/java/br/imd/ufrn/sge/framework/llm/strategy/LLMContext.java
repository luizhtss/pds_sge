package br.imd.ufrn.sge.framework.llm.strategy;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;

import java.io.IOException;

public class LLMContext {
    private ILLMProvider strategy;

    public void setStrategy(ILLMProvider strategy) {
        this.strategy = strategy;
    }


    public Relatorio gerarRelatorioBaseAcademico(String data, MatriculaDiscente  matriculaDiscente) throws InterruptedException, IOException {
        return strategy.gerarRelatorioBaseAcademico(data, matriculaDiscente);
    }
}
