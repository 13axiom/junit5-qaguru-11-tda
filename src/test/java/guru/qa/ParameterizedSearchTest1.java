//первая версия; нет оптимизации; тест на каждый новый кейс даже если они похожи
package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ParameterizedSearchTest1 {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка отображения поисковых результатов в Яндексе для запроса \"Selenide\"")
    void selenideSearchTest1() {
        // ввести в поле поиска Selenide
        Selenide.$("#text").setValue("Selenide");

        // нажать кнопку "Найти"
        Selenide.$("button[type=submit]").click();

        //ER: На странице с результатами есть карточка с текстом Selenide
        Selenide.$$("li.serp-item").find(text("Selenide")).shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка отображения поисковых результатов в Яндексе для запроса \"JUnit 5\"")
    void junitSearchTest1() {
        // ввести в поле поиска "JUnit 5"
        Selenide.$("#text").setValue("JUnit 5");

        // нажать кнопку "Найти"
        Selenide.$("button[type=submit]").click();

        //ER: На странице с результатами есть карточка с текстом Selenide
        Selenide.$$("li.serp-item").find(text("JUnit 5")).shouldBe(visible);
    }
}
