package org.example.lesson6;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenSuggestPage extends BasePage{
    public WomenSuggestPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//ul[contains(@class,'submenu')]//a[@title='T-shirts']")
    private WebElement tShirtsButton;

    @Step("Клик на кнопку Tshirts")
    public TShirtsPage clickTShirtsButton(){
        tShirtsButton.click();
        return new TShirtsPage(driver);
    }
}
