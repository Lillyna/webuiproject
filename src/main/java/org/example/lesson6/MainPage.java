package org.example.lesson6;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
        mainMenuBlock = new MainMenuBlock(driver);
    }

    @FindBy(xpath="//a[@class='login']")
    private WebElement signInButton;

    public MainMenuBlock mainMenuBlock;

    @Step("Клик на кнопку login")
    public LoginPage clickSignInButton(){
        signInButton.click();
        return new LoginPage(driver);
    }


}
