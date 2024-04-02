package br.imd.ufrn.sge.models.relatorio.tipos;

import br.imd.ufrn.sge.models.relatorio.Relatorio;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PESSOAL")
public class RelatorioPessoal extends Relatorio {

}