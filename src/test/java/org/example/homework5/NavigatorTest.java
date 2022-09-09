package org.example.homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigatorTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String NAVIGATOR_BASE_URL = "https://xn--52-kmc.xn--80aafey1amqq.xn--d1acj3b/";

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
    void chooseCourseTest(){
        waitAndClick("//div[@class='element']//input[@name='municipality']//ancestor::div[@class='element']");
        waitAndClick("//div[.='г.о.г. Нижний Новгород']");
        waitAndClick("//div[@class='__element-container form-select__value' and .='Организатор']");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated((
                By.xpath("//div[@class='__element-container form-select__options form-select__options_opened']" +
                        "//input[@class='form-select__filter-input']"))));
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='__element-container form-select__options form-select__options_opened']" +
                "//input[@class='form-select__filter-input']")))
                .click()
                .sendKeys("пароходство")
                .build()
                .perform();
        waitAndClick("//div[.='Нижегородское детское речное пароходство']");
        waitAndClick("//button//child::span[.='Найти']");
        webDriverWait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'34-morskoe-delo')]"))));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[contains(@href,'34-morskoe-delo')]")));
        waitAndClick("//a[contains(@href,'34-morskoe-delo')]");
        webDriverWait.until(ExpectedConditions.urlContains("34-morskoe-delo"));
        Assertions.assertTrue(driver.getCurrentUrl().equals("https://xn--52-kmc.xn--80aafey1amqq.xn--d1acj3b/program/34-morskoe-delo"));
    }

    private void waitAndClick(String xpath){
        webDriverWait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))));
        driver.findElement(By.xpath(xpath)).click();
    }

    @AfterEach
    void quitBrowser(){
        driver.quit();
    }
}
