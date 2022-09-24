package org.example.homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BooksPage extends MainPage {
    public BooksPage(WebDriver driver) {
        super(driver);
    }

    private final String fistBookPath = "//div[@data-title='Лучшее']/div/div[1]";
    @FindBy(xpath=fistBookPath)
    private WebElement firstBookInCarusel;

    @Step("Выбираем первую книгу в карусели")
    public BookPage caruselBookClick(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fistBookPath)));
        actions.moveToElement(firstBookInCarusel)
                .click()
                .build()
                .perform();
        return new BookPage(driver);
    }
}
