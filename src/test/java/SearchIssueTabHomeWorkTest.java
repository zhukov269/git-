import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SearchIssueTabHomeWorkTest {

    private static final String REPOSITORY = "allure-framework/allure2";

    @BeforeEach
    public void beforeEachConfig() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение Issues")
    @DisplayName("Проверка Issues с помощью чистого Selenide")
    public void testIssueSearch() {
        open("https://github.com");
        $(".search-input").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("[data-content=Issues]").shouldHave(text("Issues"));
    }


    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение Issues")
    @DisplayName("Проверка Issues с помощью Lambda шагов")
    public void testLambdaStep() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issues текста", () -> {
            $("[data-content=Issues]").shouldHave(text("Issues"));
        });

    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение Issues")
    @DisplayName("Проверка Issues с помощью шагов с аннотацией @Step")
    public void testAnnotatedStep() {
        SearchIssueTabHomeWorkWebTest steps = new SearchIssueTabHomeWorkWebTest();


        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssuesTitle();
    }
}
