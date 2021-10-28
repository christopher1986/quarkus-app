package nl.coinance.cryptocurrency.service;

import nl.coinance.cryptocurrency.model.Role;
import nl.coinance.cryptocurrency.repository.RoleRepository;
import nl.coinance.cryptocurrency.web.enums.RoleName;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class RoleService {

    private final RoleRepository roleRepository;

    @Inject
    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getRolesByName(final List<RoleName> names) {
        return roleRepository.findAllByName(names);
    }

}
