package br.imd.ufrn.sge.repository.auth;


import br.imd.ufrn.sge.models.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByLogin(String login);

    @Query("SELECT u FROM Usuario u WHERE u.dadosPessoais.id = :id")
    Optional<Usuario> findIdDadosPessoaisById(Long id);

}
