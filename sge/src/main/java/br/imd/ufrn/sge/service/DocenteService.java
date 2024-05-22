package br.imd.ufrn.sge.service;

import br.imd.ufrn.sge.exceptions.IdJaExisteException;
import br.imd.ufrn.sge.exceptions.IdNaoEncontradoException;
import br.imd.ufrn.sge.exceptions.NomeNaoEncontradoException;
import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.repository.DadosPessoaisRepository;
import br.imd.ufrn.sge.repository.DocenteRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    public boolean dadosPessoaisJaExistem(Docente docente) {
        List<Docente> dadosPessoaisJaExistem = docenteRepository.findByDadosPessoais(docente.getDadosPessoais().getId());
        return !dadosPessoaisJaExistem.isEmpty();
    }
    public boolean docenteExiste(Long id) {
        Optional<Docente> docenteExistente = encontrarPorId(id);

        return docenteExistente.isPresent();

    }

    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    public List<Docente> findByName(@NotNull String name) {
        if(name.isEmpty()){throw new NomeNaoEncontradoException();}
        return docenteRepository.findByName(name);
    }

    public Optional<Docente> encontrarPorId(Long id) {
        if (docenteRepository.findById(id).isEmpty()){
            throw new IdNaoEncontradoException();
        }
        return docenteRepository.findById(id);
    }

    @Transactional
    public Docente salvar(Docente docente) {
          if (dadosPessoaisJaExistem(docente)){
              throw new IdJaExisteException();
          }
        return docenteRepository.save(docente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!docenteExiste(id)){
            throw new IdNaoEncontradoException();
        }
    docenteRepository.deleteById(id);
}


    public Optional<Docente> buscarDocentePorIdPessoa(Long idPessoa) {
        return docenteRepository.findByDadosPessoaisId(idPessoa);
    }
}