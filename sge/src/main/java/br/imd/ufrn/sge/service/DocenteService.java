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

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    public boolean dadosPessoaisJaExistem(Docente docente) {
        Optional<Docente> dadosPessoaisJaExistem = docenteRepository.findByDadosPessoaisId(docente.getDadosPessoais().getId());
        return dadosPessoaisJaExistem.isPresent();
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
          docente.setMatricula(gerarMatricula());
        return docenteRepository.save(docente);
    }

    private  String gerarMatricula() {
        int anoAtual = Year.now().getValue();

        Random random = new Random();
        int numero = random.nextInt(100000);

        return String.format("S%04d%05d", anoAtual, numero);
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