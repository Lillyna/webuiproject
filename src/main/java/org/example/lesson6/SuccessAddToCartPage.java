package org.example.lesson6;



import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessAddToCartPage extends BasePage
{
    public SuccessAddToCartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    private WebElement totalSumma;

    private final String iconOkXpathLocator = "//i[@class='icon-ok']";
    @FindBy(xpath = iconOkXpathLocator)
    private WebElement iconOk;

    public SuccessAddToCartPage checkTotalSumma(String expectedSumma){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconOkXpathLocator)));
        Assertions.assertEquals(expectedSumma, totalSumma.getText());
        return this;
    }
}
