import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {
    @BeforeAll
    static void  browserSetUp() {
        Configuration.remote="https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.pageLoadStrategy = "eager";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Tags({
            @Tag("WEB"),
            @Tag("Regress"),
            @Tag("Ignore")
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список фильмов")
    @Tag("WEB")
    @DisplayName(" Проверка не пустого вывода страницы при поиске фильмов")
    @ValueSource(strings = {
            "Аватар",
            "Зелёная Миля",
    })
    void searchWithValueSourceShouldReturnResults(String movieTitle) {
        open("https://www.imdb.com/");
        $("#suggestion-search").setValue(movieTitle).pressEnter();

        $("section[data-testid='find-results-section-title']")
                .$$("li").shouldHave(sizeGreaterThan(0));

        $("section[data-testid='find-results-section-title'] li:first-child")
                .shouldHave(text(movieTitle));
    }

    @ParameterizedTest(name = "Для поискового запроса {0} должны быть видны и название, и год фильма")
    @DisplayName(" Проверка поиска фильма по названию и году")
    @Tag("Regress")
    @CsvSource({
            "Аватар, 2009",
            "Зелёная миля, 1999",
    })
    void searchWithYearShouldReturnCorrectMovie(String movieTitle, int year) {
        open("https://www.imdb.com/");
        $("#suggestion-search").setValue(movieTitle + " " + year).pressEnter();

        $("section[data-testid='find-results-section-title'] li:first-child")
                .shouldHave(text(movieTitle))
                .shouldHave(text(String.valueOf(year)));
    }
    @CsvFileSource(resources = "/film.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} должен быть указан год {1}")
    @DisplayName(" Проверка поиска фильма по названию и году")
    @Tag("WEB" )
    @Tag("Regress")
    void searchResultsShouldContainExpectedFilmAndYear (String movieTitle, int year) {
        open("https://www.imdb.com/");
        $("#suggestion-search").setValue(movieTitle + " " + year).pressEnter();

        $("section[data-testid='find-results-section-title'] li:first-child")
                .shouldHave(text(movieTitle))
                .shouldHave(text(String.valueOf(year)));
    }

    @Test
    @Tag("Regress")
    @Tag("Ignore")
    void successfulSearchTestSportbox() {
        open("https://news.sportbox.ru//");
        $("#smart_search").click();
        $("#smart_search").setValue("Аршавин").pressEnter();
        $(".terms-list").shouldHave(Condition.not(Condition.empty));

//        $("[id=search]").shouldHave(text("https://selenide.org"));

    }

    @Test
    @Tag("WEB")
    void successfulSearchTestGit() {
        open("https://www.github.com/");
        $(".input-button").click();
        $("#query-builder-test").setValue("java").pressEnter();
        $$(".cSURfY").shouldHave(sizeGreaterThan(0));
//        $("[class=main__center]").shouldHave(text("https://selenide.org"));
//        $("[class=Box-sc-g0xbh4-0 kzfhBO search-match prc-Text-Text-0ima0]")
} }