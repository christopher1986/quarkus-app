package nl.coinance.cryptocurrency.web.mapper;

import nl.coinance.cryptocurrency.security.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PasswordMapper {

    private final PasswordEncoder passwordEncoder;

    @Inject
    public PasswordMapper(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PasswordToEncodedPassword
    public String toEncodedPassword(final CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
