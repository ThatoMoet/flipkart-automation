package tests;

import com.thato.pages.HomePage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.LoggerHelper;

import java.util.List;
import java.util.Map;

public class NavigationTest extends BaseTest {
    private HomePage homePage;
    private List<Map<String, String>> data;
    private static final Logger logger = LoggerHelper.logger(NavigationTest.class);

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        homePage.closeLoginModal();
        data = ExcelReader.readTestData("Navigation");
    }

    @Test(groups = "smoke", priority = 14, enabled = true)
    public void electronicsPageLoads() {
        logger.info("Running TC014 - " + data.get(0).get("Test Case Name"));
        homePage.clickCategory("electronics");
        Assert.assertTrue(driver.getTitle().contains("Electronics"), "Electronics page not loaded");
        logger.info("TC014 passed");
    }

    @Test(groups = "smoke", priority = 15, enabled = true)
    public void searchBarIsVisible() {
        logger.info("Running TC015 - " + data.get(1).get("Test Case Name"));
        Assert.assertTrue(homePage.isSearchBarVisible(), "Search bar not visible");
        logger.info("TC015 passed");
    }

    @Test(groups = "smoke", priority = 16, enabled = true)
    public void pageURLIsCorrect() {
        logger.info("Running TC016 - " + data.get(2).get("Test Case Name"));
        Assert.assertTrue(driver.getCurrentUrl().contains("flipkart.com"), "Wrong URL");
        logger.info("TC016 passed");
    }

    @Test(groups = "regression", priority = 17, enabled = true)
    public void searchUpdatesTitle() {
        logger.info("Running TC017 - " + data.get(3).get("Test Case Name"));
        homePage.searchFor("laptop");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("laptop"), "Title not updated after search");
        logger.info("TC017 passed");
    }

    @Test(groups = "regression", priority = 18, enabled = true)
    public void groceryPageLoads() {
        logger.info("Running TC018 - " + data.get(4).get("Test Case Name"));
        homePage.clickCategory("grocery");
        Assert.assertTrue(driver.getCurrentUrl().contains("grocery"), "Grocery page not loaded");
        logger.info("TC018 passed");
    }

    @Test(groups = "regression", priority = 19, enabled = true)
    public void homePageURLCorrect() {
        logger.info("Running TC019 - " + data.get(5).get("Test Case Name"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.flipkart.com/", "Homepage URL incorrect");
        logger.info("TC019 passed");
    }

    @Test(groups = "regression", priority = 20, enabled = true)
    public void searchBarPlaceholderVisible() {
        logger.info("Running TC020 - " + data.get(6).get("Test Case Name"));
        Assert.assertTrue(homePage.isSearchBarVisible(), "Search bar placeholder not visible");
        logger.info("TC020 passed");
    }
}