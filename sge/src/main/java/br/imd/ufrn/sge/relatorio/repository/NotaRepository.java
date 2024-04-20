package br.imd.ufrn.sge.relatorio.repository;

import br.imd.ufrn.sge.models.discente.Discente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Discente, Long> {
    //TODO: Criar Repository de nota
}