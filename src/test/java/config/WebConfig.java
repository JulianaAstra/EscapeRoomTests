package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:web.properties",
        "system:properties"
})
public interface WebConfig extends Config {

    @Key("browser")
    String browser();

    @Key("version")
    String version();

    @Key("windowSize")
    String windowSize();

    @Key("baseUrl")
    String baseUrl();

    @Key("remote")
    @DefaultValue("")
    String remote();

    @Key("pageLoadStrategy")
    String pageLoadStrategy();
}