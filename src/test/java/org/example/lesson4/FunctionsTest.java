package org.example.lesson4;



import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.example.lesson4.Functions.isPolindrome;



public class FunctionsTest {
    private static Logger logger = LoggerFactory.getLogger("FunctionsTest");
    @BeforeAll
static void beforeAll(){
        logger.info("����� ����������� ���� ��� ����� ����� ��������");
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

}
@BeforeEach
void beforeEach(){
    //WebDriver driver = new ChromeDriver();
    logger.error("����� ����������� ����� ������ ������");
}
    @Test
    @Disabled("broken")
    @DisplayName("����� �������� ���������� � �������� ����������� ��������, ����������� � ����� isPolindrome")
    void givenPolindromeWhenCallIsPolindromeMethodThenTrue(){
        boolean result = isPolindrome("1234321");
        Assertions.assertTrue(result);
        assertThat(isPolindrome("1234321")).isTrue();
    }
    @Test
    @Tag("smoke")
    @DisplayName("����� �������� ���������� � ������ ����������� ��������, ����������� � ����� isPolindrome")
    void polindromeTest(){
        boolean result = isPolindrome("123321");
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123321", "1234321"})
    @DisplayName("����� �������� ���������� � ������ ����������� ��������, ����������� � ����� isPolindrome")
    void polindromeTestWithDataProvider(String word){
        boolean result = isPolindrome(word);
        Assertions.assertTrue(result);
    }
    @AfterAll
    static void afterAll(){
        System.out.println("����� ����������� ���� ��� ����� ���� �������");
    }
    @AfterEach
    void afterEach(){
        //driver.quit();
        System.out.println("����� ����������� ����� ������� �����");
    }

    @ParameterizedTest
    @CsvSource({"123, false", "123321, true"})
    void csvTest(String word, boolean expectedResult) {
        boolean actualResult = isPolindrome(word);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("catAndAgeDataProvider")
    void catEqualsAgeTest(Cat cat, Integer age){
        Assertions.assertEquals(cat.getAge(), age);

    }

    private static Stream<Arguments> catAndAgeDataProvider(){
        return Stream.of(
                Arguments.of(new Cat("Test1", 10), 10),
                Arguments.of(new Cat("Test2", 11), 11),
                Arguments.of(new Cat("Test3", 12), 12)
        );
    }

}
