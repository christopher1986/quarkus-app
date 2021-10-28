package nl.coinance.cryptocurrency.service;

import nl.coinance.cryptocurrency.model.User;
import nl.coinance.cryptocurrency.repository.UserRepository;
import nl.coinance.cryptocurrency.security.PasswordEncoder;
import nl.coinance.cryptocurrency.security.exception.BadCredentialsException;
import nl.coinance.cryptocurrency.web.dto.CredentialsDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Inject
    public AuthService(final UserRepository userRepository,final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(final CredentialsDTO credentials) {
        final String encodedPassword = passwordEncoder.encode(credentials.getPassword());
        return userRepository.findByCredentials(credentials.getUsername(), encodedPassword)
                .orElseThrow(() -> new BadCredentialsException("Unable to authenticate user with the provided credentials"));
    }

}
