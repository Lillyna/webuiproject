package org.example.homework6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        driver.get(NAVIGATOR_BASE_URL);
    }

    @Test
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
        driver.quit();
    }
}
