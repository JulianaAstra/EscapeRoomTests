package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.*;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    protected static final WebConfig webConfig = WebConfigReader.Instance.read();
    protected static final ApiConfig apiConfig = ApiConfigReader.Instance.read();

    @BeforeAll
    static void setupConfig() {
        ProjectConfig projectConfig = new ProjectConfig(webConfig, apiConfig);
        projectConfig.webConfig();
        projectConfig.apiConfig();


//        Configuration.remote= System.getProperty("remote");
//        Configuration.baseUrl = System.getProperty("baseUrl", "https://escape-room-neon.vercel.app/");
//        Configuration.browser = System.getProperty("browser", "chrome");
//        Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
//        Configuration.browserVersion = System.getProperty("version", "128");
//        Configuration.pageLoadStrategy = "eager";
//        RestAssured.baseURI = System.getProperty("baseUri", "https://grading.design.htmlacademy.pro");
//        RestAssured.basePath = System.getProperty("basePath", "/v1/escape-room/");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
