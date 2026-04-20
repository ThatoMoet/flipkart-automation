package com.thato.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerHelper;
import utils.WaitHelper;

public class BasePage {
    protected  WebDriver driver ;
    private static final Logger logger = LoggerHelper.logger(BasePage.class);

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    public void waitForVisible(By locator){
       WaitHelper.waitForVisible(driver, locator);
    }
}
