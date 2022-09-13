package org.example.lesson6;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
   public LoginPage(WebDriver driver){
       super(driver);
   }
   @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitButton;

    public MainPage login(String login, String password){
        webDriverWait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();
        return new MainPage(driver);
    }
}
