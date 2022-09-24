package org.example.homework6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void setExpectedPrice(String expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    private String expectedPrice;
    final String basketDefaultProdCount = "basket-default-prod-count";
    final String basketDefaultSumprice = "basket-default-sumprice-discount";

    @FindBy(id = basketDefaultProdCount)
    private WebElement prodCountText;

    @FindBy(id = basketDefaultSumprice)
    private WebElement prodSum;

    @Step("Проверяем соответствие цены")
    public CartPage verifyProdCount(String prodCount){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(basketDefaultProdCount)));
        Assertions.assertEquals(prodCount,driver.findElement(By.id(basketDefaultProdCount)).getText());
        return this;
    }
    @Step("Проверяем сумму заказа")
    public CartPage verifyProdSum(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(basketDefaultSumprice)));
        Assertions.assertEquals(expectedPrice.replaceAll("\\s+",""),
                driver.findElement(By.id(basketDefaultSumprice)).getText().replaceAll("\\s+",""));
        return this;
    }

}
