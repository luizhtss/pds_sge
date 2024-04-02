package br.imd.ufrn.sge.relatorio;

import br.imd.ufrn.sge.models.relatorio.Relatorio;

public interface IRelatorioProvider {
    Relatorio gerarRelatorioBase(String data);
}
