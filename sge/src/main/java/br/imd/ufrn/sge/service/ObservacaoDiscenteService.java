package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.exceptions.MatriculaDiscenteNaoEncontradaException;
import br.imd.ufrn.sge.models.discente.ObservacaoDiscente;
import br.imd.ufrn.sge.repository.ObservacaoDiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObservacaoDiscenteService {

    @Autowired
    private ObservacaoDiscenteRepository observacaoDiscenteRepository;

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    public List<ObservacaoDiscente> listarTodos() {
        return observacaoDiscenteRepository.findAll();
    }

    public List<ObservacaoDiscente> encontrarPorMatriculaDiscente(Long idMatriculaDiscente) {
        return observacaoDiscenteRepository.findByMatriculaDiscenteId(idMatriculaDiscente);
    }

    public ObservacaoDiscente salvarObservacao(ObservacaoDiscente observacaoDiscente) throws MatriculaDiscenteNaoEncontradaException{

        if (observacaoDiscente.getMatriculaDiscente() == null) {
            throw new IllegalArgumentException("Matrícula do discente não informada.");
        }

        if (matriculaDiscenteService.encontrarMatriculaPorIdDiscente(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new MatriculaDiscenteNaoEncontradaException("Matrícula do discente não encontrada.");

        return observacaoDiscenteRepository.save(observacaoDiscente);
    }

    public ObservacaoDiscente editarObservacao(ObservacaoDiscente observacaoDiscente) throws Exception{

        if (observacaoDiscente.getMatriculaDiscente() == null) {
            throw new IllegalArgumentException("Matrícula do discente não informada.");
        }

        if (matriculaDiscenteService.encontrarMatriculaPorIdDiscente(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new MatriculaDiscenteNaoEncontradaException("Matrícula do discente não encontrada.");

        if (observacaoDiscenteRepository.findByMatriculaDiscenteId(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new IllegalArgumentException("Observação não encontrada.");

        return observacaoDiscenteRepository.save(observacaoDiscente);
    }

    public void deletarObservacao(ObservacaoDiscente observacaoDiscente) throws IllegalArgumentException{
        if (observacaoDiscenteRepository.findByMatriculaDiscenteId(observacaoDiscente.getMatriculaDiscente().getId()).isEmpty())
            throw new IllegalArgumentException("Observação não encontrada.");

        observacaoDiscenteRepository.delete(observacaoDiscente);
    }
}