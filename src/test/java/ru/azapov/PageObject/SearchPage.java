package ru.azapov.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class SearchPage {

    public WebDriver driver;

    @FindBy(css = "[data-zone-name = \"category-link\"]")
    private WebElement category;

    @FindBy(css = "h1")
    private WebElement searchPageTitle;

    @FindBy(css = "[data-tid=\"e5a342aa\"]")
    private WebElement electronicCategory;

    @FindBy(css = "#glpricefrom")
    private WebElement costFrom;

    @FindBy(css = "[data-autotest-id=\"7893318\"]")
    private WebElement brandFilter;

    @FindBy(css = "[data-autotest-id=\"product-snippet\"] h3")
    private WebElement productSnippetTitle;

    @FindBy(css = "[data-tid=\"7de1258a\"]")
    private WebElement popover;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void checkSearchPageTitle(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(searchPageTitle, categoryName));
    }

    public void checkSearchPageTitleAfter(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(popover, "Найдено"));
        wait.until(ExpectedConditions.textToBePresentInElement(searchPageTitle, categoryName));
    }

    public void fillCostFrom(String cost) {
        costFrom.sendKeys(cost);
    }

    public void clickCheckBox(String[] brand) {
        for (int i = 0; i< brand.length; i++)
        brandFilter.findElement(By.xpath("//span[text()='" + brand[i] + "']")).click();
    }

    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(productSnippetTitle));
        return productSnippetTitle.getText();
    }

    public void clickProductSnippetTitle() {
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.elementToBeClickable(productSnippetTitle));
        productSnippetTitle.click();
    }
}
