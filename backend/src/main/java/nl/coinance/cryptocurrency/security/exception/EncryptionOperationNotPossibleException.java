package nl.coinance.cryptocurrency.security.exception;

public class EncryptionOperationNotPossibleException extends RuntimeException {

    public EncryptionOperationNotPossibleException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
