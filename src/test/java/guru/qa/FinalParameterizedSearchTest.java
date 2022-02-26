/*
//третья версия;  оптимизация: на примере параметризованнх тестов с несколькими входящими данными

package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ValueSource(strings = {"Selenide", "JUnit 5"})
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в Яндексе для запроса \"{0}\"")
    void commonSearchTest(String testData) {
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type=submit]").click();

        Selenide.$$("li.serp-item").find(text(testData)).shouldBe(visible);
    }

    @CsvSource
    @ParameterizedTest
    void complexSearchTest(){}

    static Stream<Arguments> mixedArgTestDataProvider(){
        return Stream.of(
                Arguments.of("Selenide", List.of(1,2,4), true),
                Arguments.of("JUnit 5",List<1,2,6>,false)
        );
    }



    @MethodSource (value ="mixedArgTestDataProvider")
    @ParameterizedTest
    void mixedArgSearchTest(String firstArg, List<Integer> seconfArg, boolean thirdArg){
        System.out.println("String: "+firstArg+", list: " +seconfArg+", bool? - "+thirdArg);

    }

    //ниже параметризированный тест разделенный на отдельные тесты
    @Test
    @DisplayName("Проверка отображения поисковых результатов в Яндексе для запроса \"Selenide\"")
    void selenideSearchTest() {
        Selenide.$("#text").setValue("Selenide");
        Selenide.$("button[type=submit]").click();

        Selenide.$$("li.serp-item").find(text("Selenide")).shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка отображения поисковых результатов в Яндексе для запроса \"Selenide\"")
    void junitSearchTest() {
        Selenide.$("#text").setValue("JUnit5");
        Selenide.$("button[type=submit]").click();

        Selenide.$$("li.serp-item").find(text("JUnit5")).shouldBe(visible);
    }
}
*/
