package br.imd.ufrn.sge.relatorio.interfaces;

import br.imd.ufrn.sge.models.relatorio.Relatorio;

public interface IRelatorioProvider {
    Relatorio gerarRelatorioBase(String data);
}
