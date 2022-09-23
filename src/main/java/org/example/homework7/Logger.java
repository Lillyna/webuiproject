package org.example.homework7;

import io.qameta.allure.Allure;
import org.example.lesson7.AdditionalLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

import static org.openqa.selenium.OutputType.BYTES;

public class Logger implements WebDriverListener{
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Ищем элемент по локатору " + locator);
        Allure.step("Ищем элемент по локатору " + locator);
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
        logger.info("Клик по элементу ", element.getText());
        Allure.step("Клик по элементу " + element.getText());
    }


    @Override
    public void beforeQuit(WebDriver driver){
        Allure.addAttachment("Скриншот перед закрытием сайта",new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(BYTES)));
    }
}
