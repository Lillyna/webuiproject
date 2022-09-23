package org.example.lesson7;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

import static org.openqa.selenium.OutputType.BYTES;

public class AdditionalLogger implements WebDriverListener {

    private static Logger logger = LoggerFactory.getLogger(AdditionalLogger.class);
    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Ищем элеvент по локатору " + locator);
        Allure.step("Ищем элемент по локатору " + locator);
    }
    @Override
    public void beforeQuit(WebDriver driver){
        Allure.addAttachment("Скриншот перед закрытием сайта",new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(BYTES)));
    }
}
