package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotHelper {
    private static final Logger logger = LoggerHelper.logger(ScreenshotHelper.class);

    public static String capture(WebDriver driver, String filename){
        try{
        File dir = new File("test-output/screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File destination = new File(dir, filename + "_" + timestamp + ".png");
            FileUtils.copyFile(src, destination);
            return destination.getAbsolutePath();

        }
        catch (IOException e){
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
