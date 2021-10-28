package nl.coinance.cryptocurrency.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "nl.coinance.security.encryption")
public interface EncryptionConfig {

    @ConfigProperty(name = "salt")
    String getSalt();

    @ConfigProperty(name = "iteration-count")
    int getIterationCount();

    @ConfigProperty(name = "key-length")
    int getKeyLength();

}
