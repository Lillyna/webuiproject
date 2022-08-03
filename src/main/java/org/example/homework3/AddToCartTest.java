package org.example.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class AddToCartTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://www.yves-rocher.ru/");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        try{
            driver.findElement(
                    (By.xpath("//button[@class='button form_button button_secondary max-width_default m-l_default " +
                            "button_submit button_geolocation']"))).click();
        } catch (NoSuchElementException e){
            // не очень-то и хотелось
        }

        driver.findElement(By.xpath("//input[@class='header-search-form-input']")).sendKeys("vert");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex width_100p m-b_S " +
                "search-suggestion-product-item'][1]")));

        //Выбираем первый товар из предложенных
        driver.findElement(By.xpath("//div[@class='flex width_100p m-b_S search-suggestion-product-item'][1]")).click();

        //Увеличиваем количество товара для заказа до 2 штук
        driver.findElement(By.xpath("//button[@class='flex_1_1_auto has-border_none background_white min-width_S bold " +
                "text_XXXXXL height_40 p-x_0 text_left']")).click();
        webDriverWait.until(ExpectedConditions.attributeToBe(
                driver.findElement(By.xpath("//input[@id='quantity']")), "value","2"));

        //Запоминаем цену и считаем стоимость сразу двух штук
        String price = driver.findElement(By.xpath("//div[@data-js='big-add-to-cart-bt']" +
                "/parent::div/parent::form/preceding-sibling::input"))
                .getAttribute("data-one-stock-product-discount-price");
        BigDecimal amountExpected = BigDecimal.valueOf(Double.valueOf(price)).multiply(BigDecimal.valueOf(2L));

        driver.findElement(By.xpath("//div[@data-js='big-add-to-cart-bt']/button")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Перейти в корзину']")));
        driver.findElement(By.xpath("//a[text()='Перейти в корзину']")).click();

        List<WebElement> cartPositions = driver.findElements(By.xpath("//div[@id='cart-product-list']/child::div/child::div/child::div/child::div"));
        assert cartPositions.size() == 1; // в корзине только один товар

        String amountActual = driver.findElement(By.xpath("//div[.='Сумма']/following-sibling::span")).getText();
        assert amountActual.contains(amountExpected.toString()); // стоимость корзины = стоимости товара дважды
        driver.quit();
    }

}
