package org.example.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.example.lesson7.AdditionalLogger;
import org.example.lesson7.TestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

@Story("���������� � �������")
@ExtendWith(TestExtension.class)
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
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        driver.get("http://automationpractice.com/index.php");
    }
    @Test
    @TmsLink("123")
    @Feature("�������")
    @DisplayName("�������� ������� � �������")
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
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for(LogEntry log: logEntries){
            Allure.addAttachment("Элемент лога браузера ", log.getMessage());
        }
        driver.quit();
    }
}
