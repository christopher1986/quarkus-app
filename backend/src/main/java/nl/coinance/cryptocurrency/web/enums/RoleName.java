package nl.coinance.cryptocurrency.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleName implements CodeEnum {

    ADMIN("ADMIN", "Administrator role"),
    USER("USER", "User role");

    private final String code;
    private final String description;
}
