package br.imd.ufrn.sge.dto;

import br.imd.ufrn.sge.models.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReqResponseDTO {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private long idDadosPessoais;
    private Usuario user;
}
