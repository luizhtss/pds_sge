package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.discente.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Long>{

    @Query("SELECT d FROM Discente d WHERE d.dadosPessoais = :idPessoa")
    Optional<Discente> findByIdPessoa(Long idPessoa);

}
