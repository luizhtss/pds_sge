package br.imd.ufrn.sge.relatorio.repository;

import br.imd.ufrn.sge.models.DiscenteMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<DiscenteMateria, Long> {

    @Query("SELECT n FROM DiscenteMateria n WHERE n.matricula_discente.discente.id = :matricula_discente")
    List<DiscenteMateria> findByDiscenteMatricula(@Param("matricula_discente") Long matricula_discente);

    @Query("SELECT n FROM DiscenteMateria n WHERE n.materia.id_materia = :id_materia")
    List<DiscenteMateria> findByMateriaId(@Param("id_materia") Long id_materia);
}