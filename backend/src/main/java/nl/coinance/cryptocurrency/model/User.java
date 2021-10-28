package nl.coinance.cryptocurrency.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.coinance.cryptocurrency.web.enums.RoleName;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "users")
@ToString(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue
    @ToString.Include
    private Long id;

    private String firstName;

    private String lastName;

    @ToString.Include
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Set<Role> roles;

    public Set<String> getRoleNames() {
        return roles.stream()
                .map(Role::getName)
                .map(RoleName::getCode)
                .collect(Collectors.toSet());
    }

}
