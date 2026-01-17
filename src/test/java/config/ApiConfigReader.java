package config;

import org.aeonbits.owner.ConfigFactory;

public enum ApiConfigReader {
    Instance;

    private static final ApiConfig apiConfig =
            ConfigFactory.create(
                    ApiConfig.class,
                    System.getProperties()
            );

    public ApiConfig read() {
        return apiConfig;
    }
}