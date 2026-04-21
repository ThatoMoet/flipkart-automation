package com.thato.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerHelper;

public class HomePage extends BasePage{
    private static final Logger logger = LoggerHelper.logger(HomePage.class);

    @FindBy(xpath = "//img[contains(@src,'logo')]")
    private WebElement logo;

    @FindBy(name= "q")
    private WebElement searchBar;

    @FindBy(css = "button[aria-label='Search for Products, Brands and More']")
    private  WebElement searchButton;

    @FindBy(xpath = "//a[contains(@href,'grocery')]")
    private WebElement groceryCategory;

    @FindBy(xpath = "//div[contains(text(),'Electronics')]")
    private WebElement electronicsCategory;

    @FindBy(xpath = "//span[@role='button' and text()='X']")
    private WebElement closeModalButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoVisible(){
        logger.info("Checking logo visibility");
        return logo.isDisplayed();
    }

    public void searchFor(String term){
        logger.info("Searching for: " + term);

        searchBar.clear();
        searchBar.click();
        searchBar.sendKeys(term);
        searchBar.sendKeys(Keys.ENTER);

    }
    public boolean isCategoryVisible() {
        return electronicsCategory.isDisplayed();
    }

    public void clickCategory(String category) {
        logger.info("Clicking category: " + category);

        if (category.equalsIgnoreCase("grocery")) {
            groceryCategory.click();
        } else if (category.equalsIgnoreCase("electronics")) {
            electronicsCategory.click();
        }

    }

    public void closeLoginModal() {
        try {
            closeModalButton.click();
            logger.info("Login modal closed");
        } catch (Exception e) {
            logger.info("No login modal present");
        }
    }
}
