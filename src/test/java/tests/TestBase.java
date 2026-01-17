package tests;

import com.codeborne.selenide.WebDriverRunner;
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
    }

    @BeforeEach
    void addAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
            closeWebDriver();
        }
    }
}
