
import org.junit.jupiter.api.Test;




import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.DragAndDropOptions.to;

import static com.codeborne.selenide.Selenide.*;


public class DragAndDrop {
    @Test
    void moveElementByDragBDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        $("#column-a").dragAndDrop(to($("#column-b")));
        $("#column-a").shouldHave(text("B"));
    }
}
