package org.example.homework6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookPage extends BasePage {
    public BookPage(WebDriver driver) {
        super(driver);
    }
    //цена может быть со скидкой или без скидки
    private final String bookPricePath =
            "//span[contains(@class,'buying-price') and contains(@class,'number') and not(contains(@class,'old'))]";
    @FindBy(xpath = bookPricePath)
    private WebElement bookPrice;

    @FindBy(xpath = "//div[@class='buying-btns']/a[contains(@onclick,'shoping')]")
    private WebElement addToCartButton;

    private final String makeOrderButtonPath = "//a[.='Оформить заказ']//ancestor::div[@class='buying']";
    @FindBy(xpath = makeOrderButtonPath)
    private WebElement makeOrderButton;

    public BookPage addToCart(){
        actions.moveToElement(addToCartButton)
                .click()
                .build()
                .perform();
        return this;
    }

    public CartPage getPriceAndMakeOrder(){
        CartPage cartPage = new CartPage(driver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bookPricePath)));
        cartPage.setExpectedPrice(bookPrice.getText());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(makeOrderButtonPath)));
        actions.moveToElement(makeOrderButton)
                .click()
                .build()
                .perform();
        return cartPage;
    }
}
