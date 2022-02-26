//вторая версия;  оптимизация: объединение тестов по тестовой дате
package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ParameterizedSearchTest2 {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Selenide", "JUnit 5"})
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в Яндексе для запроса \"{0}\"") //порядковый номер аргумента из тест даты который будет передан в заголовок
    void commonSearchTest2(String testData) {
        // ввести в поле поиска {test_data}
        Selenide.$("#text").setValue(testData);

        // нажать кнопку "Найти"
        Selenide.$("button[type=submit]").click();

        //ER: На странице с результатами есть карточка с текстом {test_data}
        Selenide.$$("li.serp-item").find(text(testData)).shouldBe(visible);
    }
}
