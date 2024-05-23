package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaDiscenteRepository extends JpaRepository<MatriculaDiscente, Long> {

    @Query("SELECT m FROM MatriculaDiscente m WHERE m.discente.dadosPessoais.id = :idPessoa")
    List<MatriculaDiscente> findByIdPessoa(Long idPessoa);

    @Query("SELECT m FROM MatriculaDiscente m WHERE m.discente.dadosPessoais.id = :idPessoa AND m.ano = :ano")
    Optional<MatriculaDiscente> findByIdPessoaAndAno(Long idPessoa, int ano);

    @Query("SELECT m FROM MatriculaDiscente m WHERE m.id = :idMatricula AND m.ano = :ano")
    Optional<MatriculaDiscente> findByIdMatriculaAndAno(Long idMatricula, int ano);

    @Query("SELECT m FROM MatriculaDiscente m WHERE m.discente.id = :idDiscente")
    Optional<MatriculaDiscente> findByIdDiscente(Long idDiscente);

    @Query("SELECT m FROM MatriculaDiscente m WHERE m.matricula = :numeroMatricula")
    Optional<MatriculaDiscente> findByNumeroMatricula(String numeroMatricula);
}
