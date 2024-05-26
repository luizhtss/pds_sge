package br.imd.ufrn.sge.relatorio.repository;

import br.imd.ufrn.sge.models.DiscenteMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscenteMateriaRepository extends JpaRepository<DiscenteMateria, Long> {

    @Query("SELECT n FROM DiscenteMateria n WHERE n.matricula_discente.discente.id = :matricula_discente")
    List<DiscenteMateria> findByDiscenteMatricula(@Param("matricula_discente") Long matricula_discente);

    @Query("SELECT n FROM DiscenteMateria n WHERE n.materia.id_materia = :id_materia")
    List<DiscenteMateria> findByMateriaId(@Param("id_materia") Long id_materia);


    // Pega idMatriculaDiscente e retorna um boolean true se todas as unidades foram preenchidas
    @Query("SELECT CASE WHEN COUNT(n) = 0 THEN false ELSE true END FROM DiscenteMateria n WHERE n.matricula_discente.id = :id AND n.unidade1 IS NOT NULL AND n.unidade2 IS NOT NULL AND n.unidade3 IS NOT NULL")
    boolean todasUnidadesPreenchidas(@Param("id") Long idMatriculaDiscente);

}