package org.example.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenuBlock extends BasePage {
    public MainMenuBlock(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//li/a[@title='Women']")
    private WebElement womenButton;
    @Step("Навести курсор мыши на кнопку women")
    public WomenSuggestPage hoverWomenButton(){
        actions.moveToElement(womenButton)
                .build()
                .perform();
        return new WomenSuggestPage(driver);
    }
}
