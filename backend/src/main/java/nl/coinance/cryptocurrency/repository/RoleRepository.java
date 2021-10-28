package nl.coinance.cryptocurrency.repository;

import nl.coinance.cryptocurrency.model.Role;
import nl.coinance.cryptocurrency.web.enums.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Set<Role> findAllByName(final Iterable<RoleName> names);

}
