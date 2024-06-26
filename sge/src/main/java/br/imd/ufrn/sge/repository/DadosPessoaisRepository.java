package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.DadosPessoais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {
    @Query("SELECT p FROM DadosPessoais p WHERE YEAR(p.dataCriacao) = YEAR(:dataCriacao)")
    List<DadosPessoais> findByYearOfCreation(@Param("dataCriacao") Date dataCriacao);

    @Query("SELECT p FROM DadosPessoais p WHERE p.nome = :nome")
    List<DadosPessoais> findByName(@Param("nome") String nome);

    @Query("SELECT p FROM DadosPessoais p WHERE p.email = :email")
    Optional<DadosPessoais> findByEmail(String email);
}

