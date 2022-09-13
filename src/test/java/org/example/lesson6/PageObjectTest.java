package org.example.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void initDriver(){
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
    }
    @Test
    void putTShirtToCartTest() throws InterruptedException {
//        MainPage mainPage = new MainPage(driver);
//        mainPage.clickSignInButton();
//        new LoginPage(driver).login("Guits1929@rhyta.com", "123456");
//        new MainMenuBlock(driver).hoverWomenButton();
//        new WomenSuggestPage(driver);
        new MainPage(driver).clickSignInButton()
                .login("Guits1929@rhyta.com", "123456")
                .mainMenuBlock.hoverWomenButton()
                .clickTShirtsButton()
                .selectSize("S")
                .moveMouseToProduct()
                .checkTotalSumma("$18.51");
    }

    @AfterEach
    void killBrowser(){
        driver.quit();
    }
}
