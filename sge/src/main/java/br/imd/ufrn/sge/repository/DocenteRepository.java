package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.docente.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

  @Query("SELECT p FROM Docente p WHERE p.dadosPessoais.nome = :nome")
  List<Docente> findByName(@Param("nome") String nome);
}