package br.imd.ufrn.sge.framework.llm.strategy;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.interfaces.ILLMProvider;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;

import java.io.IOException;

public class LLMContext {
    private ILLMProvider model;

    public void setModelo(ILLMProvider model) {
        this.model = model;
    }


    public Relatorio gerarRelatorioBaseAcademico(String data, MatriculaDiscente  matriculaDiscente) throws InterruptedException, IOException {
        return model.gerarRelatorioBaseAcademico(data, matriculaDiscente);
    }
}
