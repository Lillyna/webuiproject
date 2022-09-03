package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class ActionsTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String AFISHA_BASE_URL = "https://afisha.ru/nnovgorod";

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);

    }

    @Test
    public void dragAndDropTest(){
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))
                .build()
                .perform();
        Assertions.assertEquals(driver.findElement(By.id("droppable")).getText(),"Dropped!");
    }

    @Test
    public void tabsTest() throws InterruptedException {
        driver.get("https://google.com");
        ((JavascriptExecutor)driver).executeScript("alert('text')");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(2000);
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        driver.get("http://ya.ru");
        Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);

    }

    @AfterEach
    void quitBrowser(){
        driver.quit();
    }
}
