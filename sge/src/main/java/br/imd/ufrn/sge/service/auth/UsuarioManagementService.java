package br.imd.ufrn.sge.service.auth;

import br.imd.ufrn.sge.dto.UserRegisterDTO;
import br.imd.ufrn.sge.dto.UserReqResponseDTO;
import br.imd.ufrn.sge.exceptions.AuthException;
import br.imd.ufrn.sge.models.DadosPessoais;
import br.imd.ufrn.sge.models.auth.Usuario;
import br.imd.ufrn.sge.repository.auth.UsuarioRepository;
import br.imd.ufrn.sge.service.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UsuarioManagementService {

    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;


    public UserReqResponseDTO register(UserRegisterDTO userRegisterDTO) throws AuthException{
        UserReqResponseDTO userReqResponseDTO1 = new UserReqResponseDTO();

        String matricula = userRegisterDTO.getMatricula();

        boolean isDiscente = false;

        if (matricula.toCharArray()[0] == 'S') {
            isDiscente = true;
        }else{
            isDiscente = false;
        }



        Optional<DadosPessoais> dadosPessoais = dadosPessoaisService.findByEmail(userRegisterDTO.getEmail());

        if (dadosPessoais.isEmpty())
            throw new AuthException("Dados pessoais não encontrado");


        if (userService.findByLogin(userRegisterDTO.getEmail()).isPresent())
            throw new AuthException("Login já existe no sistema.");


        Usuario user = new Usuario();
        user.setLogin(userRegisterDTO.getEmail());
        user.setRole(isDiscente ? "DISCENTE" : "DOCENTE");
        user.setSenha(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setDadosPessoais(dadosPessoais.get());
        Usuario usuario = userService.save(user);


        if (usuario.getId() >0 ){
            userReqResponseDTO1.setMessage("Usuário cadastrado com sucesso");
            userReqResponseDTO1.setStatusCode(200);
        }
        return userReqResponseDTO1;
    }

    public UserReqResponseDTO login(UserReqResponseDTO loginRequest) throws AuthException {
        UserReqResponseDTO userReqResponseDTO = new UserReqResponseDTO();

        try{
            Optional<Usuario> user =  userService.findByLogin(loginRequest.getUser().getLogin());
            if (user.isPresent()){
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUser().getLogin(), loginRequest.getUser().getPassword()));
                String token = jwtService.generateToken(user.get());
                String refreshToken = jwtService.refreshToken(new HashMap<>(),user.get());
                userReqResponseDTO.setToken(token);
                userReqResponseDTO.setRefreshToken(refreshToken);
                userReqResponseDTO.setExpirationTime("15 minutos");
                userReqResponseDTO.setMessage("Usuário autenticado com sucesso");
                userReqResponseDTO.setStatusCode(200);
            }else {
                throw new AuthException("Dados não encontrado");
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new AuthException("Usuário ou senha inválidos");
        }
        return userReqResponseDTO;
    }

    // Refresca o token
    public UserReqResponseDTO refresh(UserReqResponseDTO userReqResponseDTO) throws AuthException {
        UserReqResponseDTO userReqResponseDTO1 = new UserReqResponseDTO();
        Optional<Usuario> user = userService.findByLogin(userReqResponseDTO.getUser().getLogin());
        if (user.isPresent()){
            String token = jwtService.generateToken(user.get());
            String refreshToken = jwtService.refreshToken(new HashMap<>(),user.get());
            userReqResponseDTO1.setToken(token);
            userReqResponseDTO1.setRefreshToken(refreshToken);
            userReqResponseDTO1.setExpirationTime("15 minutos");
            userReqResponseDTO1.setMessage("Token atualizado com sucesso");
            userReqResponseDTO1.setStatusCode(200);
        }else {
            throw new AuthException("Usuário não encontrado");
        }
        return userReqResponseDTO1;
    }

    public UserReqResponseDTO getMyInfo(String login) throws AuthException {
        UserReqResponseDTO userReqResponseDTO = new UserReqResponseDTO();
        Optional<Usuario> user = userService.findByLogin(login);
        if (user.isPresent()){
           // userReqResponseDTO.setUser(user.get());
            userReqResponseDTO.setStatusCode(200);
        }else {
            throw new AuthException("Usuário não encontrado");
        }
        return userReqResponseDTO;
    }

    public UserReqResponseDTO updateUser(UserReqResponseDTO userReqResponseDTO) throws AuthException {
        UserReqResponseDTO userReqResponseDTO1 = new UserReqResponseDTO();
        Optional<Usuario> user = userService.findByLogin(userReqResponseDTO.getUser().getLogin());
        if (user.isPresent()){
            user.get().setLogin(userReqResponseDTO.getUser().getLogin());
            user.get().setSenha(passwordEncoder.encode(userReqResponseDTO.getUser().getLogin()));
            Usuario usuario = userService.save(user.get());
            if (usuario.getId() >0 ){
                userReqResponseDTO1.setMessage("Usuário atualizado com sucesso");
                userReqResponseDTO1.setStatusCode(200);
            }
        }else {
            throw new AuthException("Usuário não encontrado");
        }
        return userReqResponseDTO1;
    }
}
