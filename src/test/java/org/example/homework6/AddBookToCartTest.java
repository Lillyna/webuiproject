package org.example.homework6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.example.lesson7.AdditionalLogger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Story("Добавление в корзину")
public class AddBookToCartTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String NAVIGATOR_BASE_URL = "https://www.labirint.ru/";

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser(){
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        driver.get(NAVIGATOR_BASE_URL);
    }

    @Test
    @Feature("Корзина")
    @TmsLink("bookId")
    @DisplayName("Добавить книгу в корзину")
    public void addBookToCartTest(){
        String countProduct = "1 товар";
        new MainPage(driver).mainMenuBlock.hoverBooksButton()
                .clickAllBooks()
                .caruselBookClick()
                .addToCart()
                .getPriceAndMakeOrder()
                .verifyProdCount(countProduct)
                .verifyProdSum();

    }
    @AfterEach
    void quitBrowser(){
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for(LogEntry log: logEntries){
            Allure.addAttachment("Элемент лога браузера ", log.getMessage());
        }
        driver.quit();
    }
}
