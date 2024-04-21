package br.imd.ufrn.sge.relatorio.repository;

import br.imd.ufrn.sge.models.materia.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long>, JpaSpecificationExecutor<Materia> {

}