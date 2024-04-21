package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.materia.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long>, JpaSpecificationExecutor<Materia> {
  @Query("SELECT p FROM Materia p WHERE p.nome_materia = :nome_materia")
  List<Materia> findByName(@Param("nome_materia") String nome);

  @Query("SELECT p FROM Materia p WHERE p.id_materia = :id_materia")
  List<Materia> findById(@Param("id_materia") String id_materia);

  @Query("SELECT p FROM Materia p WHERE p.docente.id = :id_docente")
  List<Materia> findByIdDocente(@Param("id_docente") String id_docente);
}