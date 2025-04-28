import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.*;

public class Hover {

    @Test
    void successfulSearch() {

        open("https://github.com");
        $$("button[class^='HeaderMenu-link']").findBy(text("Solutions")).hover();
        $$("a[class^='HeaderMenu-dropdown-link']").findBy(text("Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform "));

}
}



