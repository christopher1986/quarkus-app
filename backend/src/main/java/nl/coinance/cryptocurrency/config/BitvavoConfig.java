package nl.coinance.cryptocurrency.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "nl.coinance.bitvavo")
public interface BitvavoConfig {

    @ConfigProperty(name = "api-key")
    String getApiKey();

    @ConfigProperty(name = "api-secret")
    String getApiSecret();

    @ConfigProperty(name = "access-window", defaultValue = "10000")
    int getAccessWindow();


}
