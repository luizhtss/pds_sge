package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.models.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {
    List<Frequencia> findByDiscenteMateria(DiscenteMateria discenteMateria);
}