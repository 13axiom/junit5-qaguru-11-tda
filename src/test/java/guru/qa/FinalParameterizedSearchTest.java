//третья версия;  оптимизация: на примере параметризованнх тестов с несколькими входящими данными

package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class FinalParameterizedSearchTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru/");

    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }


    //два набора данных, 1 строчка - 1 запуск -> внутри строчки запятая делит аргументы. Если нужна запятая внутри аргумента, то используем delimetr
    @CsvSource(value = {
            "Selenide| concise UI",
            "JUnit 5| IntelliJ IDEA"
    },
            delimiter = '|')

    @ParameterizedTest(name = "Проверка отображения поисковых результатов в Яндексе для запроса \"{0}\"")
    //0 - это порядковый номер аргумента из тест даты который будет передан в заголовок
    void complexSearchTest(String testData, String expectedData) {
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type=submit]").click();
        Selenide.$$("li.serp-item").find(text(testData)).shouldBe(visible);
        Selenide.$$("li.serp-item").find(text(expectedData)).shouldBe(visible);

    }


    //-----

    static Stream<Arguments> mixedArgTestDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", List.of(1, 2, 4), true),
                Arguments.of("JUnit 5", List.of(1, 2, 6), false)
        );
    }


    @MethodSource(value = "mixedArgTestDataProvider")
    //можно выше не писать "value =", а сразу подавать string, но так раюотает только с value в Java
    //MethodSource - наиболее универсальный способ параметризации теста (им можно заменить и CsvSource и  ValueSource, но хуже читается, визуально сложнее для восприятия
    @ParameterizedTest
    void mixedArgSearchTest(String firstArg, List<Integer> secondArg, boolean thirdArg) {
        System.out.println("String: " + firstArg + ", list: " + secondArg + ", bool? - " + thirdArg);

    }
}
