package br.imd.ufrn.sge.relatorio.relatorio.tipos;

import br.imd.ufrn.sge.relatorio.relatorio.Relatorio;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PESSOAL")
public class RelatorioPessoal extends Relatorio {

}