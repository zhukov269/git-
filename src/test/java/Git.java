import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.*;




public class Git {
    @BeforeAll
    static void beforeAll() {
       Configuration.holdBrowserOpen = true;
    }

    @Test
    void successfulSearchTestBing() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".Box-row.wiki-more-pages-link").click();
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").shouldHave(text("Soft assertions")).click();

        // Проверка наличия примера кода для JUnit5
        $$(".markdown-heading").findBy(text("JUnit5")).sibling(0).$("pre").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                        "class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));
    }
//        $("#markdown-heading").shouldHave(text("Chapters"));
//        $("[id=search]").shouldHave(text("https://selenide.org"));

    }









