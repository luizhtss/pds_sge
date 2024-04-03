package br.imd.ufrn.sge.relatorio.interfaces;

import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;

public interface IRelatorioProvider {
    Relatorio gerarRelatorioBasePessoal(String data);
    Relatorio gerarRelatorioBaseAcademico(String data);
}
