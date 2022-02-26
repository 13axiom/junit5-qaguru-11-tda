package guru.qa.lecture;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class Snippets {
    //пример1: если int в тест дате
    public class ParameterizedSearchTest2 {

        @BeforeEach
        void precondition() { Selenide.open("https://ya.ru/"); }
        @AfterEach
        void closeBrowser() { Selenide.closeWebDriver(); }

        @ValueSource(strings = {1, 2})
        @ParameterizedTest(name = "Проверка отображения поисковых результатов в Яндексе для запроса \"{0}\"")
        void commonSearchTest2(int testData) {
            Selenide.$("#text").setValue(String.valueOf(testData));
            Selenide.$("button[type=submit]").click();
            Selenide.$$("li.serp-item").find(text(testData)).shouldBe(visible);

        }
    }

    //пример2:
}
