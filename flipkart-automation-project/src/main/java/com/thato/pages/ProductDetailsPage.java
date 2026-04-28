package com.thato.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerHelper;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerHelper.logger(ProductDetailsPage.class);

    @FindBy(tagName = "h1")
    private WebElement productTitle;

    @FindBy(xpath = "//div[contains(text(),'₹')]")
    private WebElement productPrice;

    @FindBy(xpath = "//div[contains(@style,'inter_bold') and contains(@dir,'auto')]")
    private WebElement productRating;

    @FindBy(xpath= "//div[text()='Product highlights']")
    private WebElement productHighlights;

    @FindBy(xpath = "//div[text()='Similar Products']")
    private WebElement relatedProducts;


    public String getProductTitle(){
        logger.info("Getting product title");

        return productTitle.getText();
    }

    public String getProductPrice(){
        logger.info("Getting Product price");
        return productPrice.getText();
    }

    public boolean productRatingIsVisible(){
        logger.info("Checking Product Rating visibility");
        return productRating.isDisplayed();
    }

    public boolean productHighlightsVisible(){
        logger.info("Checking product Highlights");
        return productHighlights.isDisplayed();
    }

    public boolean isRelatedProductsVisible(){
        return relatedProducts.isDisplayed();
    }


}
