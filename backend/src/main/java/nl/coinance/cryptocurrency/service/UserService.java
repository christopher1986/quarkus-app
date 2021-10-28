package nl.coinance.cryptocurrency.service;

import nl.coinance.cryptocurrency.model.Role;
import nl.coinance.cryptocurrency.model.User;
import nl.coinance.cryptocurrency.repository.UserRepository;
import nl.coinance.cryptocurrency.web.dto.NewUserDTO;
import nl.coinance.cryptocurrency.web.enums.RoleName;
import nl.coinance.cryptocurrency.web.mapper.UserMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public UserService(
            final UserRepository userRepository,
            final UserMapper userMapper,
            final RoleService roleService
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    public void createUser(final NewUserDTO userDTO) {
        final User user = userMapper.toEntity(userDTO);
        user.setRoles(getRolesByName(Collections.singletonList(RoleName.USER)));

        userRepository.save(user);
    }

    private Set<Role> getRolesByName(final List<RoleName> names) {
        final Set<Role> roles = roleService.getRolesByName(names);
        final Set<RoleName> matchedRoles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toCollection(HashSet::new));
        final List<String> missingRoles = names.stream()
                .filter(not(matchedRoles::contains))
                .map(RoleName::getDescription)
                .collect(Collectors.toList());

        if (!missingRoles.isEmpty()) {
            final String message = "The following roles requested do not exist: %s";
            throw new EntityNotFoundException(String.format(message, String.join(",", missingRoles)));
        }

        return roles;
    }

}
