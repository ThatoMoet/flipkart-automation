package tests;

import com.thato.pages.HomePage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerHelper;

import java.util.List;
import java.util.Map;

public class HomePageTest extends BaseTest{
    private HomePage homePage;
    private List<Map<String, String>> data;
    private static final Logger logger = LoggerHelper.logger(HomePageTest.class);

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        homePage.closeLoginModal();
        data = ExcelReader.readTestData("HomePage");
    }

    @Test(groups = "smoke", priority = 1, enabled = true)
    public void HomePageLoads(){
        String expectedResult = data.get(0).get("Expected Result");
        logger.info("Running TC001 - " + data.get(0).get("Test Case Name"));
        String actualTitle = homePage.getTitle();
        Assert.assertTrue(actualTitle.contains("Online Shopping Site"),
                "Expected: " + expectedResult + " | Actual: " + actualTitle);
        logger.info("TC001 passed - title: " + actualTitle);
    }

    @Test(groups = "smoke", priority = 2, enabled = true)
    public void logoVisibility(){
        logger.info("Running TC002 - " + data.get(1).get("Test Case Name"));
        Assert.assertTrue(homePage.isLogoVisible());
        logger.info("TC002 passed - logo is visible");

    }

    @Test(groups = "smoke", priority = 3, enabled = true)
    public void categoryLinksVisible(){
        logger.info("Running TC003 - " + data.get(2).get("Test Case Name"));
        Assert.assertTrue(homePage.isCategoryVisible(), "Categories not visible");
        logger.info("TC003 passed - categories visible");

    }


}
