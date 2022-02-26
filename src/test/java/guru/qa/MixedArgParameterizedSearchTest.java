package guru.qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MixedArgParameterizedSearchTest {
    static Stream<Arguments> mixedArgTestDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", List.of(1, 2, 4), true),
                Arguments.of("JUnit 5", List.of(1, 2, 6), false)
        );
    }


    @MethodSource(value = "mixedArgTestDataProvider")
    //можно выше не писать "value =", а сразу подавать string, но так раюотает только с value в Java
    //MethodSource - наиболее универсальный способ параметризации теста (им можно заменить и CsvSource и  ValueSource, но хуже читается, визуально сложнее для восприятия
    @ParameterizedTest (name = "тест имени аргумента {0}")
    void mixedArgSearchTest(String firstArg, List<Integer> secondArg, boolean thirdArg) {
        System.out.println("String: " + firstArg + ", list: " + secondArg + ", bool? - " + thirdArg);

    }
}
