package nl.coinance.cryptocurrency.web.mapper;

import nl.coinance.cryptocurrency.model.User;
import nl.coinance.cryptocurrency.web.dto.NewUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", uses = { PasswordMapper.class })
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "credentials.username", target = "username")
    @Mapping(source = "credentials.password", target = "password", qualifiedBy = PasswordToEncodedPassword.class)
    @Mapping(target = "roles", ignore = true)
    User toEntity(final NewUserDTO userDTO);

}
