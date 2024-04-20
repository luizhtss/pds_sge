package br.imd.ufrn.sge.relatorio.repository;

import br.imd.ufrn.sge.models.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}

