package br.imd.ufrn.sge.relatorio.interfaces;

import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ILLMProvider {
    Relatorio gerarRelatorioBasePessoal(String data) throws IOException, InterruptedException;
    Relatorio gerarRelatorioBaseAcademico(String data) throws InterruptedException, IOException;
}
