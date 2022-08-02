package org.example.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Random;

public class DiaryTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        //peter.sh/experiments - все настройки для браузера
//        driver.get("https://diary.ru/user/login");
//        WebElement loginForm = driver.findElement(By.id("loginform-username"));
//        loginForm.sendKeys("spartalex");
//        driver.findElement(By.id("loginform-password")).sendKeys("123456");
//
//        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title=\"reCAPTCHA\"]")));
//        driver.findElement(By.xpath("//div[@class=\"recaptcha-checkbox-border\"]")).click();
//        driver.switchTo().parentFrame();
//        driver.findElement(By.id("login_btn")).click();
        driver.get("https://diary.ru");
        Cookie cookie = new Cookie("_identity_","83668234c30812b14c46bac1deda1a240770255504032650b424a75038c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%22E_QJqRaNdBrepyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");

        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        //driver.manage().deleteCookieNamed("_identity_");
        driver.findElement(By.id("writeThisDiary")).click();
        String postTitle = "test" + new Random().nextInt(1000);
        driver.findElement(By.id("postTitle")).sendKeys(postTitle);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("test");
        driver.switchTo().parentFrame();
        driver.findElement(By.id("rewrite")).click();

        //driver.findElement(By.xpath(String.format("//a[text()='%s']",postTitle))).click();
        List<WebElement> postTitles = driver.findElements(By.xpath("//a[@class='title']"));
        postTitles.stream().filter(p->p.getText().equals(postTitle)).findFirst().get().click();

        Thread.sleep(5000);


        driver.quit();
    }
}
