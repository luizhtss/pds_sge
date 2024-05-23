package br.imd.ufrn.sge.relatorio.interfaces;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;

public interface IDataFetcher {

    String fetchData(MatriculaDiscente matriculaDiscente);

}
