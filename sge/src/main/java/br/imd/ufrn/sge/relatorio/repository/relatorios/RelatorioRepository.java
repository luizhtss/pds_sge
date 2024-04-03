package br.imd.ufrn.sge.relatorio.repository.relatorios;

import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatorioRepository<T extends Relatorio> extends JpaRepository<T, Long> {


}
