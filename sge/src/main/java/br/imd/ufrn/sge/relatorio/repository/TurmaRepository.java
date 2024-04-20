package br.imd.ufrn.sge.relatorio.repository;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query("SELECT t FROM Turma t WHERE t.nome = :nome")
    List<Turma> findByName(@Param("nome") String nome);
}

