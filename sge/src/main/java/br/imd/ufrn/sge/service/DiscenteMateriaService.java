package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.config.GlobalStrategy;
import br.imd.ufrn.sge.framework.aprovacao_materia.AprovacaoSimples;
import br.imd.ufrn.sge.framework.aprovacao_materia.AprovacaoSubstituicao;
import br.imd.ufrn.sge.framework.aprovacao_materia.AprovacaoTemplate;
import br.imd.ufrn.sge.framework.aprovacao_materia.AprovacaoUFRN;
import br.imd.ufrn.sge.framework.notas.INotaStrategy;
import br.imd.ufrn.sge.framework.notas.NotaAmericanaStrategy;
import br.imd.ufrn.sge.framework.notas.NotaNormalStrategy;
import br.imd.ufrn.sge.framework.notas.NotaPonderadaStrategy;
import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.relatorio.repository.DiscenteMateriaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DiscenteMateriaService {

    @Autowired
    private GlobalStrategy globalStrategy;

    @Autowired
    private DiscenteMateriaRepository discenteMateriaRepository;

    // Autowired instances of AprovacaoTemplate implementations
    @Autowired
    private AprovacaoSimples aprovacaoSimples;
    @Autowired
    private AprovacaoSubstituicao aprovacaoSubstituicao;
    @Autowired
    private AprovacaoUFRN aprovacaoUFRN;

    // Map for AprovacaoTemplate
    private Map<String, AprovacaoTemplate> mapAprovacaoTemplate;

    @PostConstruct
    public void init() {
        mapAprovacaoTemplate = Map.of(
                "1", aprovacaoSimples,
                "2", aprovacaoSubstituicao,
                "3", aprovacaoUFRN
        );
    }

    private final Map<String, INotaStrategy> mapStrategy = Map.of(
            "1", new NotaNormalStrategy(),
            "2", new NotaAmericanaStrategy(),
            "3", new NotaPonderadaStrategy()
    );


    public List<DiscenteMateria> listarTodos() {
        return discenteMateriaRepository.findAll();
    }

    public Optional<DiscenteMateria> encontrarPorId(Long id) {
        return discenteMateriaRepository.findById(id);
    }

    public List<DiscenteMateria> encontrarPorMatriculaDiscente(String matricula_discente) {
        return discenteMateriaRepository.findByDiscenteMatricula(matricula_discente);
    }

    public List<DiscenteMateria> encontrarPorIdMateria(Long id_materia) {
        return discenteMateriaRepository.findByMateriaId(id_materia);
    }

    public boolean todasUnidadesPreenchidas(Long idMatriculaDiscente) {
        return discenteMateriaRepository.todasUnidadesPreenchidas(idMatriculaDiscente);
    }

    @Transactional
    public DiscenteMateria salvar(DiscenteMateria nota, DiscenteMateria nota_nova) {
        if(nota_nova.getUnidade1() != null)
            nota.setUnidade1(nota_nova.getUnidade1());
        if(nota_nova.getUnidade2() != null)
            nota.setUnidade2(nota_nova.getUnidade2());
        if(nota_nova.getUnidade3() != null || nota_nova.getProvaFinal() != null) {
            if (nota_nova.getUnidade3() != null)
                nota.setUnidade3(nota_nova.getUnidade3());
            if (nota_nova.getProvaFinal() != null) {
                nota.setProvaFinal(nota_nova.getProvaFinal());
            }
            String strategyChoice = globalStrategy.getEscolhaStrategy().toLowerCase();
            AprovacaoTemplate template = mapAprovacaoTemplate.get(strategyChoice);
            template.aprovacaoMethod(nota, nota.getFrequencias());
        }
        return discenteMateriaRepository.save(nota);
    }

    public float calcularNota(float u1, float u2,float u3) {
        INotaStrategy strategy = mapStrategy.get(globalStrategy.getEscolhaStrategy().toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de nota inválido");
        }
        return strategy.calcularMedia(u1, u2, u3);
    }


}
