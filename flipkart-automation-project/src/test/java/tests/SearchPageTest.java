package tests;

import com.thato.pages.HomePage;
import com.thato.pages.SearchResultsPage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.LoggerHelper;

import java.util.List;
import java.util.Map;

public class SearchPageTest extends BaseTest {
    private SearchResultsPage searchPage;
    private HomePage homePage;
    private List<Map<String, String>> data;
    private static final Logger logger = LoggerHelper.logger(SearchPageTest.class);

    @BeforeMethod
    public void setupPages(){
    homePage = new HomePage(driver);
    homePage.closeLoginModal();
    data = ExcelReader.readTestData("SearchPage");
    }

    @Test
    public void resultsContainer(){
        String searchTerm = data.get(1).get("Search Term");
        logger.info("Running TC005 - " + data.get(1).get("Test Case Name"));
        homePage.searchFor(searchTerm);
        searchPage = new SearchResultsPage(driver);

        Assert.assertTrue(searchPage.isResultsContainerVisible());
        logger.info("TC005 passed");

    }

    @Test
    public void returnResults(){
        String searchTerm = data.get(0).get("Search Term");
        logger.info("Getting search Term");

        homePage.searchFor(searchTerm);
        searchPage = new SearchResultsPage(driver);

        Assert.assertTrue(searchPage.getResultsCount() > 0);
        logger.info("TC004 passed - results count: " + searchPage.getResultsCount());

    }

    @Test
    public void sortLowToHigh(){
        String option = data.get(2).get("Search Term");
        logger.info("Getting search Term");
        homePage.searchFor(option);
        searchPage = new SearchResultsPage(driver);

        searchPage.selectSortOption(option);
        Assert.assertTrue(searchPage.getResultsCount() > 0);
        logger.info("TC006 passed - sorted low to high");


    }

    @Test
    public void sortByPopularity(){
        String searchTerm = data.get(3).get("Search Term");
        logger.info("Getting search Term");
        homePage.searchFor(searchTerm);
        searchPage = new SearchResultsPage(driver);

        searchPage.selectSortOption("Popularity");
        Assert.assertTrue(searchPage.getResultsCount() > 0);
        logger.info("TC007 passed - sorted by popularity");

    }

    @Test
    public void resultsCountGreaterThanZero(){
        String searchTerm = data.get(4).get("Search Term");
        logger.info("Getting search Term");

        homePage.searchFor(searchTerm);
        searchPage = new SearchResultsPage(driver);

        Assert.assertTrue(searchPage.getResultsCount() > 0);
        logger.info("TC004 passed - results count: " + searchPage.getResultsCount());

    }

}

