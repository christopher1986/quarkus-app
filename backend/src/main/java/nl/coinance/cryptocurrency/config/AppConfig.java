package nl.coinance.cryptocurrency.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "nl.coinance.app")
public interface AppConfig {

    @ConfigProperty(name = "year")
    int getYear();

}
