package ru.azapov.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.azapov.Config.ConfigProperties;
import ru.azapov.PageObject.DetailsPage;
import ru.azapov.PageObject.SearchPage;
import ru.azapov.PageObject.StartPage;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

public class FindObjectByParam {

    public static WebDriver driver;
    public static StartPage startPage;
    public static SearchPage searchPage;
    public static DetailsPage detailsPage;
    public static String tvPrice = ConfigProperties.getProperty("price");
    public static String category = "Электроника";
    public static String electronicCategory = "Телевизоры";


    @BeforeMethod
    public static void setUp() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        startPage = new StartPage(driver);
        searchPage = new SearchPage(driver);
        detailsPage = new DetailsPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigProperties.getProperty("startPage"));
    }

    @DataProvider(name = "test")
    public static Object[][] brandModel() {
        return new Object[][] {
                {"LG"},
                {"Samsung"},
                {"LG", "Samsung"}
        };
    }

    @Test(description = "Check TV price and Brand", dataProvider = "test")
    public void testFindObjectByParam(String[] brand) throws Exception{
        getYandexMarketPage();
        clickCategory();
        clickElectronicCategory();
        editFilters(brand);
        checkBrand(brand);

    }

    @AfterMethod
    public static void tearDown() {
        driver.quit();

    }

    @Step("Step 1. Check Title Page")
    public static void getYandexMarketPage(){
        Assert.assertEquals(driver.getTitle(), "Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов");
        Allure.addAttachment("Check Title Page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Step 2. Choose category and check Page")
    public void clickCategory() {
        startPage.chooseCategory(category);
        Assert.assertEquals(driver.getTitle(), category + " — купить на Яндекс.Маркете");
        startPage.checkCategotyTitle(category);
        Allure.addAttachment("Check Electronic Category Page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Step 3. Choose electronic category and check Search Page")
    public void clickElectronicCategory() {
        startPage.chooseElectronicCategory(electronicCategory);
        Assert.assertEquals(driver.getTitle(), electronicCategory + " — купить на Яндекс.Маркете");
        searchPage.checkSearchPageTitle(electronicCategory);
        Allure.addAttachment("Check Search Page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Step 4. Select filters and check title")
    public void editFilters(String[] brand) {
        searchPage.fillCostFrom(tvPrice);
        searchPage.clickCheckBox(brand);
        if (brand.length == 1) searchPage.checkSearchPageTitleAfter(electronicCategory + " " + brand[0]);
        else searchPage.checkSearchPageTitleAfter(electronicCategory);
        Allure.addAttachment("Results after using filters", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Step 5. Click on the first position and checks")
    public void checkBrand(String[] brand) throws Exception {
        String modelName = searchPage.getProductName();
        searchPage.clickProductSnippetTitle();
        Thread.sleep(5000);
        detailsPage.checkTitle(modelName);
        detailsPage.checkPrice(tvPrice);
        detailsPage.checkBrand(brand);
        Allure.addAttachment("View TV model", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}