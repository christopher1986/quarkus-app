package nl.coinance.cryptocurrency.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewUserDTO extends UserDTO {

    @NotNull
    private CredentialsDTO credentials;

}
