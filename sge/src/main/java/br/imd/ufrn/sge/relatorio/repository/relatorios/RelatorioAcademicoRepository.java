package br.imd.ufrn.sge.relatorio.repository.relatorios;

import br.imd.ufrn.sge.relatorio.relatorio.tipos.RelatorioAcademico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioAcademicoRepository extends RelatorioRepository<RelatorioAcademico> {

    @Query("SELECT r FROM RelatorioAcademico r WHERE r.matriculaDiscente = :matriculaDiscente")
    RelatorioAcademico findByMatriculaDiscente(String matriculaDiscente);
}
