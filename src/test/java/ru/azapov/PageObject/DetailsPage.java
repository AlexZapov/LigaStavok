package ru.azapov.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.util.List;

public class DetailsPage {

    public WebDriver driver;

    @FindBy(css = "h1")
    private WebElement detailsPageTitle;

    @FindBy(css = ".n-product-top-offers-orderer > div:nth-child(1) > .cia-vs:nth-child(1) [data-zone-name=\"top-offer\"] [data-tid=\"23fad448\"] > span > span:nth-child(1)")
    private List<WebElement> topPrice;

    @FindBy(css = "[itemprop=\"itemListElement\"]:nth-child(2) span")
    private WebElement brandName;

    public DetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void checkTitle(String title) {
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(detailsPageTitle));
        Assert.assertEquals(detailsPageTitle.getText(), title);
    }

    public void checkPrice(String enteredPrice) throws Exception {
        Integer checkedPrice = Integer.parseInt(enteredPrice);
        for (int i = 0; i< topPrice.size(); i++)
            if (Integer.parseInt(topPrice.get(i).getText().replace(" ", "")) > checkedPrice)
                System.out.println("Предложение интересное.");
            else throw new Exception("Выпало неверное предложение!");

    }

    public void checkBrand(String[] brand) throws Exception {
        if (brand.length == 1) Assert.assertEquals(brandName.getText(), brand[0]);
        else
            for (int i = 0; i< brand.length; i++) {
                if (brand[i].equals(brandName.getText())) {
                    break;
                }
            }

    }
}
