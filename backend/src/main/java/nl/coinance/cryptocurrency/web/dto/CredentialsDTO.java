package nl.coinance.cryptocurrency.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredentialsDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
