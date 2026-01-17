package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.MutableCapabilities;

import java.util.Map;

public class ProjectConfig {
    private final WebConfig webConfig;
    private final ApiConfig apiConfig;

    public ProjectConfig(WebConfig webConfig, ApiConfig apiConfig) {
        this.webConfig = webConfig;
        this.apiConfig = apiConfig;
    }

    public void apiConfig() {
        RestAssured.baseURI = apiConfig.baseUri();
        RestAssured.basePath = apiConfig.basePath();
    }

    public void webConfig() {
        Configuration.baseUrl = System.getProperty("baseUrl", webConfig.baseUrl());
        Configuration.browser = System.getProperty("browser", webConfig.browser());
        Configuration.browserVersion = System.getProperty("version", webConfig.version());
        Configuration.browserSize = System.getProperty("windowSize", webConfig.windowSize());
        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();

        String remote = webConfig.remote();
        if (remote != null && !remote.isEmpty()) {
            Configuration.remote = remote;
            Configuration.browserCapabilities = new MutableCapabilities();
            Configuration.browserCapabilities.setCapability("selenoid:options",
                    Map.<String, Object>of(
                            "enableVNC", true,
                            "enableVideo", true
                    )
            );
        }
    }

}