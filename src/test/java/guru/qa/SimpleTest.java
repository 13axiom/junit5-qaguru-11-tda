package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс с простыми тестами")
public class SimpleTest {

    @Test
    @DisplayName("Ожидаем зеленый тест")
    void simpleGreenTest() {
        assertTrue (3>2);
    }
    @Test
    @DisplayName("Ожидаем красный тест")
    void simpleRedTest() {
        assertTrue (3<2);
    }
    @Test
    @Disabled("bug: JIRA-123") //дизейблит тест пока ег оанпример фиксят и он не будет запускатсья и попадать в отчет
    void simpleBrokenYellowTest() {
        throw new IllegalStateException("Broken ;(");
    }

}