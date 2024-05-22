package br.imd.ufrn.sge.service.auth;

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


    public UserReqResponseDTO register(UserReqResponseDTO userReqResponseDTO) throws AuthException{
        UserReqResponseDTO userReqResponseDTO1 = new UserReqResponseDTO();

        Optional<DadosPessoais> dadosPessoais = dadosPessoaisService.encontrarPorId(userReqResponseDTO.getIdDadosPessoais());

        if (dadosPessoais.isEmpty())
            throw new AuthException("Dados pessoais não encontrado");

        if (userService.findUserByIdDadosPessoais(userReqResponseDTO.getIdDadosPessoais()).isPresent())
            throw new AuthException("Usuário já cadastrado no sistema.");

        if (userService.findByLogin(userReqResponseDTO.getUser().getLogin()).isPresent())
            throw new AuthException("Login já existe no sistema.");


        Usuario user = new Usuario();
        user.setLogin(userReqResponseDTO.getUser().getLogin());
        user.setRole(userReqResponseDTO.getUser().getRole());
        user.setSenha(passwordEncoder.encode(userReqResponseDTO.getUser().getPassword()));
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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUser().getLogin(), loginRequest.getUser().getPassword()));
            Optional<Usuario> user =  userService.findByLogin(loginRequest.getUser().getLogin());
            if (user.isPresent()){
                String token = jwtService.generateToken(user.get());
                String refreshToken = jwtService.refreshToken(new HashMap<>(),user.get());
                userReqResponseDTO.setToken(token);
                userReqResponseDTO.setRefreshToken(refreshToken);
                userReqResponseDTO.setExpirationTime("15 minutos");
                userReqResponseDTO.setMessage("Usuário autenticado com sucesso");
                userReqResponseDTO.setStatusCode(200);
            }else {
                throw new AuthException("Dados pessoais não encontrado");
            }
        } catch (Exception e){
            throw new AuthException("Usuário ou senha inválidos");
        }
        return userReqResponseDTO;
    }
}
