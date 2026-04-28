package tests;

import com.thato.pages.HomePage;
import com.thato.pages.ProductDetailsPage;
import com.thato.pages.SearchResultsPage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ExcelReader;
import utils.LoggerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductPageTest extends BaseTest{
    private static final Logger logger = LoggerHelper.logger(ProductPageTest.class);
    private List<Map<String, String>> data;
    private HomePage homePage;

    @BeforeMethod
    public void setUpPages(){
        homePage = new HomePage(driver);
    homePage.closeLoginModal();
    data = ExcelReader.readTestData("ProductPage");}

    @Test
    public void productTitleDisplayed(){
        String searchTerm = data.get(0).get("Search Term");
        logger.info("Running TC009 - " + data.get(0).get("Test Case Name"));
        homePage.searchFor(searchTerm);
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        searchPage.clickFirstProduct();
        // product opens in new tab - switch to it
        switchToNewTab();

        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertTrue(product.getProductTitle().contains("Samsung"),
                "Product title not displayed");
        logger.info("TC009 passed - title: " + product.getProductTitle());

    }

    @Test
    public void productPriceVisible(){
        String searchTerm = data.get(0).get("Search Term");
        logger.info("Running TC010 - " + data.get(1).get("Test Case Name"));
        homePage.searchFor(searchTerm);
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        searchPage.clickFirstProduct();

        switchToNewTab();

        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertFalse(product.getProductPrice().isEmpty(), "Price not visible");


    }

    @Test
    public void productRatingVisible() {
        String searchTerm = data.get(2).get("Search Term");
        logger.info("Running TC011 - " + data.get(2).get("Test Case Name"));
        homePage.searchFor(searchTerm);
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        searchPage.clickFirstProduct();
        switchToNewTab();
        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertTrue(product.productRatingIsVisible(), "Rating not visible");
        logger.info("TC011 passed");
    }

    @Test
    public void productHighlightsVisible(){
        String searchTerm = data.get(3).get("Search Term");
        logger.info("Running TC012 - " + data.get(3).get("Test Case Name"));
        homePage.searchFor(searchTerm);
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        searchPage.clickFirstProduct();
        switchToNewTab();
        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertTrue(product.productHighlightsVisible(), "Highlights not visible");
        logger.info("TC012 passed");
    }

    @Test
    public void relatedProductsVisible(){
        String searchTerm = data.get(3).get("Search Term");
        logger.info("Running TC012 - " + data.get(4).get("Test Case Name"));
        homePage.searchFor(searchTerm);
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        searchPage.clickFirstProduct();
        switchToNewTab();
        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertTrue(product.isRelatedProductsVisible(), "Highlights not visible");
        logger.info("TC012 passed");
    }
}

