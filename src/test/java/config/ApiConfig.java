package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:api.properties",
        "system:properties"
})
public interface ApiConfig extends Config {
    @Key("baseUri")
    String baseUri();

    @Key("basePath")
    String basePath();
}
