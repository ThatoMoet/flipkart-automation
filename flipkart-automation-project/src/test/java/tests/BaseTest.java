package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
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
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.LoggerHelper;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

@Listeners(listeners.TestListener.class)
public class BaseTest {
    public static ExtentReports extent;
    protected Properties config;
    public WebDriver driver;
    private static final Logger logger = LoggerHelper.logger(BaseTest.class);
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeSuite
    public void setUpReportAndConfig() {
        new File("test-output").mkdirs();

        config = ConfigReader.getConfig();
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

    }


    @BeforeMethod
    public void setUpBrowser() {
        config = ConfigReader.getConfig(); // add this line
        String browser = config.getProperty("browser");
        boolean headless = Boolean.parseBoolean(config.getProperty("headless"));

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions co = new ChromeOptions();
            if (headless) {
                co.addArguments("--headless");
            }
            driver = new ChromeDriver(co);


        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions ffo = new FirefoxOptions();

            if (headless) {
                ffo.addArguments("--headless");
            }
            driver = new FirefoxDriver(ffo);


        } else {
            logger.error("Unsupported Browser");
        }

        int implicitWait = Integer.parseInt(ConfigReader.getConfig().getProperty("implicit.wait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        driver.get(config.getProperty("base.url"));
    }


    protected void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ;
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
    }


