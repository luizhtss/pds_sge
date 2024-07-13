package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.materia.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>, JpaSpecificationExecutor<Materia> {

    @Query("SELECT m FROM Materia m JOIN Docente d ON d.id = m.docente.id WHERE d.id = :idDocente")
    Optional<Materia> findByDocenteId(@Param("idDocente") Long idDocente);

    @Query("SELECT m FROM Materia m JOIN m.turma tm WHERE tm.id = :idTurma")
    List<Materia> findByTurmaId(@Param("idTurma") Long idTurma);


}