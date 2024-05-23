package br.imd.ufrn.sge.service.auth;


import br.imd.ufrn.sge.exceptions.AuthException;
import br.imd.ufrn.sge.models.auth.Usuario;
import br.imd.ufrn.sge.repository.auth.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    /**
     * Busca um usuário pelo id dos dados pessoais
     * @param id
     * @return
     * @throws AuthException
     */
    public Optional<Usuario> findUserByIdDadosPessoais(Long id) throws AuthException {
        return usuarioRepository.findIdDadosPessoaisById(id);
    }

    public Optional<Usuario> findByLogin(String login){
        return usuarioRepository.findByLogin(login);
    }


    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


}
