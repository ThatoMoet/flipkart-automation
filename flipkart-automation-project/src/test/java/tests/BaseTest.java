package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.LoggerHelper;

import java.time.Duration;
import java.util.Properties;

public class BaseTest{
    protected static ExtentReports extent;
    protected Properties config;
    protected WebDriver driver;
    private static final Logger logger = LoggerHelper.logger(BaseTest.class);

    @BeforeSuite
    public void setUpReportAndConfig(){
        config =  ConfigReader.getConfig();
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

    }

    @BeforeMethod
    public void setUpBrowser(){
        String browser = ConfigReader.getConfig().getProperty("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.getConfig().getProperty("headless"));

        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions co = new ChromeOptions();
            if (headless){
                co.addArguments("--headless");
            }
            driver = new ChromeDriver(co);



        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions ffo = new FirefoxOptions();

            if (headless){
                ffo.addArguments("--headless");
            }
            driver = new FirefoxDriver(ffo);


        }
        else {
            logger.error("Unsupported Browser");
        }

        int implicitWait = Integer.parseInt(ConfigReader.getConfig().getProperty("implicit.wait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        driver.get(config.getProperty("base.url"));}



    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        };
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
    }


