package nl.coinance.cryptocurrency.security;

import nl.coinance.cryptocurrency.config.EncryptionConfig;
import nl.coinance.cryptocurrency.security.exception.EncryptionOperationNotPossibleException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Singleton
public class PBKDF2PasswordEncoder implements PasswordEncoder {

    private final static String ALGORITHM = "PBKDF2WithHmacSHA512";

    private final EncryptionConfig encryptionConfig;

    @Inject
    public PBKDF2PasswordEncoder(final EncryptionConfig encryptionConfig) {
        this.encryptionConfig = encryptionConfig;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            final byte[] encodedBytes = SecretKeyFactory.getInstance(ALGORITHM)
                    .generateSecret(new PBEKeySpec(
                            rawPassword.toString().toCharArray(),
                            encryptionConfig.getSalt().getBytes(),
                            encryptionConfig.getIterationCount(),
                            encryptionConfig.getKeyLength()))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(encodedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            final String message = String.format("Unable to encrypt password with %s algorithm", ALGORITHM);
            throw new EncryptionOperationNotPossibleException(message, e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
