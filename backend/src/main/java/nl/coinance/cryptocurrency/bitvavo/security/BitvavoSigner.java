package nl.coinance.cryptocurrency.bitvavo.security;

import nl.coinance.cryptocurrency.config.BitvavoConfig;
import org.apache.commons.codec.binary.Hex;
import org.jboss.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class BitvavoSigner {

    private static final Logger LOGGER = Logger.getLogger(BitvavoSigner.class);
    private static final String HMAC_SHA_256 = "HmacSHA256";

    private final BitvavoConfig bitvavoConfig;
    private final Mac mac;

    @Inject
    public BitvavoSigner(final BitvavoConfig bitvavoConfig) {
        this.bitvavoConfig = bitvavoConfig;
        this.mac = getMacInstance();
    }

    public String sign(final SignatureInfo info) {
        try {
            mac.init(new SecretKeySpec(bitvavoConfig.getApiSecret().getBytes(StandardCharsets.UTF_8), HMAC_SHA_256));
            return new String(Hex.encodeHex(mac.doFinal(info.getBytes(StandardCharsets.UTF_8))));
        } catch (InvalidKeyException e) {
            LOGGER.error("Key is inappropriate for initializing MAC.");
            throw new RuntimeException("Unable to sign with inappropriate key", e);
        }
    }

    private Mac getMacInstance() {
        try {
            return Mac.getInstance(HMAC_SHA_256);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("No Provider available for HmacSHA256 algorithm");
            throw new RuntimeException("No HmacSHA256 algorithm available", e);
        }
    }

}
