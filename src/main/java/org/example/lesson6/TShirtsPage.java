package org.example.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TShirtsPage extends BasePage{
    public TShirtsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[.='Size']/ancestor::div[@class='layered_filter']//a")
    private List<WebElement> sizesList;

    @Step("Выбрать размер")
    public TShirtsPage selectSize(String size){
        sizesList.stream().filter(s-> s.getText().contains(size)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    private WebElement productElement;

    public SuccessAddToCartPage moveMouseToProduct(){
        actions.moveToElement(productElement)
                .build()
                .perform();
        addToCartButton.click();
        return new SuccessAddToCartPage(driver);
    }

    @FindBy(xpath = "//span[.='Add to cart']")
    private WebElement addToCartButton;

}
