package org.example.homework6;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
        mainMenuBlock = new MainMenuBlock(driver);
    }
    public org.example.homework6.MainMenuBlock mainMenuBlock;
}
