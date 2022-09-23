package org.example.homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BooksExpandListPage extends BasePage {
    public BooksExpandListPage(WebDriver driver) {
        super(driver);
    }

    private final String allBooksPath = "//a[.='Все книги' and contains(@class,'menu')]";
    @FindBy(xpath = allBooksPath )
    private WebElement allBooks;

    @Step("Выбираем \"Все книги\"")
    public BooksPage clickAllBooks(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(allBooksPath)));
        actions.moveToElement(allBooks)
                .click()
                .build()
                .perform();
        return new BooksPage(driver);
    }
}
