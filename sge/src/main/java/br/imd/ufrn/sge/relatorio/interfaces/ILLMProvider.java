package br.imd.ufrn.sge.relatorio.interfaces;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;

import java.io.IOException;

public interface ILLMProvider {
    Relatorio gerarRelatorioBasePessoal(String data) throws IOException, InterruptedException;
    Relatorio gerarRelatorioBaseAcademico(String data, MatriculaDiscente matriculaDiscente) throws InterruptedException, IOException;
}
