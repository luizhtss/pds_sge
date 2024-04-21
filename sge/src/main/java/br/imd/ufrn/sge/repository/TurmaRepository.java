package br.imd.ufrn.sge.repository;

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

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Turma t JOIN t.discentes d WHERE d.id = :idDiscente AND t.id = :idTurma")
    boolean isDiscenteMatriculado(@Param("idDiscente") Long idDiscente, @Param("idTurma") Long idTurma);

    @Query("UPDATE Turma t SET t.discentes = :idMatriculaDiscente WHERE t.id = :idTurma")
    void matricularDiscente(Long idMatriculaDiscente, Long idTurma);
}

