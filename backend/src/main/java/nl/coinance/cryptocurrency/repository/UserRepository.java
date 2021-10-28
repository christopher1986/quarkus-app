package nl.coinance.cryptocurrency.repository;

import nl.coinance.cryptocurrency.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.username = ?1 and password = ?2")
    Optional<User> findByCredentials(final String username, final String password);

}
