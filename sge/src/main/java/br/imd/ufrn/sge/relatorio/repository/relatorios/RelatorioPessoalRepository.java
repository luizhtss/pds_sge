package br.imd.ufrn.sge.relatorio.repository.relatorios;

import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioPessoal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioPessoalRepository extends RelatorioRepository<RelatorioPessoal> {

    @Query("SELECT r FROM RelatorioPessoal r WHERE r.matriculaDiscente = :matriculaDiscente")
    RelatorioPessoal findByMatriculaDiscente(String matriculaDiscente);

}
