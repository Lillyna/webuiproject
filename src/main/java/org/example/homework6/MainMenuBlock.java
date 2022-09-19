package org.example.homework6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainMenuBlock extends BasePage{

    private final String booksPath = "//a[.='Книги']";
    public MainMenuBlock(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = booksPath)
    private WebElement books;

    public BooksExpandListPage hoverBooksButton(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(booksPath)));
        actions.moveToElement(books)
                .build()
                .perform();
        return new BooksExpandListPage(driver);
    }
}
