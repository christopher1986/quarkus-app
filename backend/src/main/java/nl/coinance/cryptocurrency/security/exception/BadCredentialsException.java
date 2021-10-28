package nl.coinance.cryptocurrency.security.exception;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(final String message) {
        super(message);
    }

}
