package com.thato.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerHelper;

import java.util.List;

public class SearchResultsPage extends BasePage {
   private static final Logger logger = LoggerHelper.logger(SearchResultsPage.class);

    @FindBy(css = "div[data-id]")
    private List <WebElement> searchResults;

    @FindBy(className = "WNv7PR")
    private List<WebElement> sortOptions;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void applyPriceFilter(String min, String max){
        logger.info("Price filter not yet implemented");

    }
    public boolean isResultsContainerVisible(){
        return !searchResults.isEmpty();

    }

    public int getResultsCount(){
        logger.info("Getting search results count");
        return searchResults.size();
    }

    public void selectSortOption(String option){
        for (WebElement sortOp: sortOptions){
            if (sortOp.getText().equalsIgnoreCase(option)){
                sortOp.click();
                logger.info("Selected sort option");
                break;

            }
        }

    }

}
