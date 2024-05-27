package br.imd.ufrn.sge.controller.auth;


import br.imd.ufrn.sge.dto.UserRegisterDTO;
import br.imd.ufrn.sge.dto.UserReqResponseDTO;
import br.imd.ufrn.sge.service.auth.UsuarioManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserManagementAuthController {

    @Autowired
    private UsuarioManagementService usuarioManagementService;

    @PostMapping("/register")
    public ResponseEntity<UserReqResponseDTO> register(@RequestBody UserRegisterDTO userReqResponseDTO){
        try {
            UserReqResponseDTO userReqResponseDTO1 = usuarioManagementService.register(userReqResponseDTO);
            return ResponseEntity.ok().body(userReqResponseDTO1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserReqResponseDTO> login(@RequestBody UserReqResponseDTO userReqResponseDTO){
        try {
            UserReqResponseDTO userReqResponseDTO1 = usuarioManagementService.login(userReqResponseDTO);
            return ResponseEntity.ok().body(userReqResponseDTO1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserReqResponseDTO> refresh(@RequestBody UserReqResponseDTO userReqResponseDTO){
        try {
            UserReqResponseDTO userReqResponseDTO1 = usuarioManagementService.refresh(userReqResponseDTO);
            return ResponseEntity.ok().body(userReqResponseDTO1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(null);
        }
    }

}
