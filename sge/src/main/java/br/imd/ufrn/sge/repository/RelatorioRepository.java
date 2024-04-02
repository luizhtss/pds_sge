package br.imd.ufrn.sge.repository;

import br.imd.ufrn.sge.models.relatorio.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
}
