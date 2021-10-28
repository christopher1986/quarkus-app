package nl.coinance.cryptocurrency.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.coinance.cryptocurrency.web.enums.RoleName;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue
    @ToString.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

}
