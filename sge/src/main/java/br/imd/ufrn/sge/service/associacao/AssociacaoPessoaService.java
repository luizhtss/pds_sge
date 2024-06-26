package br.imd.ufrn.sge.service.associacao;

import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.discente.Discente;
import br.imd.ufrn.sge.models.discente.MatriculaDiscente;
import br.imd.ufrn.sge.models.docente.Docente;
import br.imd.ufrn.sge.service.DadosPessoaisService;
import br.imd.ufrn.sge.service.DiscenteService;
import br.imd.ufrn.sge.service.DocenteService;
import br.imd.ufrn.sge.service.MatriculaDiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;
import java.util.Random;

@Service
public class AssociacaoPessoaService {
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    @Autowired
    DiscenteService discenteService;

    @Autowired
    DocenteService docenteService;

    @Autowired
    MatriculaDiscenteService matriculaDiscenteService;

    public MatriculaDiscente associarPessoaDiscente(Long idPessoa) throws IllegalArgumentException {
     Optional<DadosPessoais> dp =  dadosPessoaisService.encontrarPorId(idPessoa);
        if(dp.isPresent()){
            int anoAtual = Year.now().getValue();

            Optional<MatriculaDiscente> matriculaDiscente = matriculaDiscenteService.encontrarMatriculaPorIdPessoaEAno(idPessoa, anoAtual);

            if (matriculaDiscente.isPresent())
                throw new IllegalArgumentException("Discente já matriculado no ano de " + anoAtual);

            Optional<Discente> discenteDB = discenteService.buscarDiscentePorIdPessoa(idPessoa);

            Discente discente = discenteDB.orElseGet(() -> discenteService.salvarDiscente(new Discente.Builder()
                    .withDadosPessoais(dp.get())
                    .build()));

            DadosPessoais dadosPessoais = dp.get();

            String matricula = gerarMatricula();

            MatriculaDiscente matriculaDiscenteBuilder = new MatriculaDiscente.Builder()
                    .withDiscente(discente)
                    .withAno(anoAtual)
                    .withMatricula(matricula).build();

            return matriculaDiscenteService.salvarMatricula(matriculaDiscenteBuilder);
        }else {
            throw new IllegalArgumentException("Pessoa com o ID " + idPessoa + " não encontrada");
        }
    }

    public Docente associarPessoaDocente(Long idPessoa) throws IllegalArgumentException {
        Optional<DadosPessoais> dp = dadosPessoaisService.encontrarPorId(idPessoa);
        if (dp.isPresent()) {
            Optional<Docente> docenteDB = docenteService.buscarDocentePorIdPessoa(idPessoa);

            Docente docente = docenteDB.orElseGet(() -> docenteService.salvar(new Docente.Builder()
                    .withDadosPessoais(dp.get())
                    .build()));

            return docente;
        } else {
            throw new IllegalArgumentException("Pessoa com o ID " + idPessoa + " não encontrada");
        }
    }


    private  String gerarMatricula() {
        int anoAtual = Year.now().getValue();

        Random random = new Random();
        int numero = random.nextInt(100000);

        return String.format("%04d%05d", anoAtual, numero);
    }
}
