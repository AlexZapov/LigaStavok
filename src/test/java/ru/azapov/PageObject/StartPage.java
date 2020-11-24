package ru.azapov.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class StartPage {

    public WebDriver driver;

    @FindBy(css = "[data-zone-name = \"category-link\"]")
    private WebElement category;

    @FindBy(css = "h1")
    private WebElement categoryTitle;

    @FindBy(css = "[data-tid=\"e5a342aa\"]")
    private WebElement electronicCategory;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void chooseCategory(String categoryName) {
        category.findElement(By.xpath("//span[text()='" + categoryName + "']")).click();
    }

    public void checkCategotyTitle(String categoryName) {
        if (categoryTitle.isDisplayed()) Assert.assertEquals(categoryTitle.getText(), categoryName);
    }

    public void chooseElectronicCategory(String electronics) {
        electronicCategory.findElement(By.xpath("//a[text()='" + electronics + "']")).click();
    }
}
