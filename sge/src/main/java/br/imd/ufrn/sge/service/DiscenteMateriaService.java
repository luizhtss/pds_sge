package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.framework.notas.INotaStrategy;
import br.imd.ufrn.sge.framework.notas.NotaAmericanaStrategy;
import br.imd.ufrn.sge.framework.notas.NotaNormalStrategy;
import br.imd.ufrn.sge.framework.notas.NotaPonderadaStrategy;
import br.imd.ufrn.sge.framework.notas.strategy.NotaContext;
import br.imd.ufrn.sge.models.DiscenteMateria;
import br.imd.ufrn.sge.relatorio.repository.DiscenteMateriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DiscenteMateriaService {

    @Autowired
    private DiscenteMateriaRepository discenteMateriaRepository;

    private final Map<String, INotaStrategy> mapStrategy = Map.of(
            "normal", new NotaNormalStrategy(),
            "americana", new NotaAmericanaStrategy(),
            "ead", new NotaPonderadaStrategy()
    );

//    private final Map<String, AprovacaoTemplate> mapStatusTemplate = Map.of(
//            "normal", new AprovacaoSimples(),
//            "americana", new AprovacaoSubstituicao(),
//            "ead", new AprovacaoUFRN()
//    );
    public List<DiscenteMateria> listarTodos() {
        return discenteMateriaRepository.findAll();
    }

    public Optional<DiscenteMateria> encontrarPorId(Long id) {
        return discenteMateriaRepository.findById(id);
    }

    public List<DiscenteMateria> encontrarPorMatriculaDiscente(Long matricula_discente) {
        return discenteMateriaRepository.findByDiscenteMatricula(matricula_discente);
    }

    public List<DiscenteMateria> encontrarPorIdMateria(Long id_materia) {
        return discenteMateriaRepository.findByMateriaId(id_materia);
    }

    public boolean todasUnidadesPreenchidas(Long idMatriculaDiscente) {
        return discenteMateriaRepository.todasUnidadesPreenchidas(idMatriculaDiscente);
    }

    @Transactional
    public DiscenteMateria salvar(DiscenteMateria nota) {
        return discenteMateriaRepository.save(nota);
    }

    public float calcularNota(DiscenteMateria discenteMateria, String tipo) {
        INotaStrategy strategy = mapStrategy.get(tipo.toLowerCase());
        NotaContext context = new NotaContext();
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de nota inválido");
        }
        context.setNotaStrategy(strategy);
        return context.calcularMedia(discenteMateria.getUnidade1(), discenteMateria.getUnidade2(), discenteMateria.getUnidade3());
    }


}
