package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.docente.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Long>{

    @Query("SELECT d FROM Discente d WHERE d.dadosPessoais.id = :idPessoa")
    Optional<Discente> findByIdPessoa(Long idPessoa);

    @Query("SELECT p FROM Discente p WHERE p.dadosPessoais.id = :id")
    List<Discente> findByDadosPessoais(@Param("id") Long id);

}
