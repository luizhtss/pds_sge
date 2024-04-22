package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query("SELECT t FROM Turma t WHERE t.nome = :nome")
    List<Turma> findByName(@Param("nome") String nome);

    @Query("SELECT t FROM Turma t WHERE t.id = :id")
    Optional<Turma> findById(@Param("id") Long id);

    @Query("SELECT d FROM Discente d JOIN MatriculaDiscente md ON md.discente.id = d.id WHERE md.turma.id = :idTurma")
        Optional<Discente> findMatriculados(@Param("idTurma") Long idTurma);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM MatriculaDiscente d WHERE d.matricula = :idDiscente AND d.turma.id = :idTurma")
    boolean isDiscenteMatriculado(@Param("idDiscente") Long idDiscente, @Param("idTurma") Long idTurma);

}

