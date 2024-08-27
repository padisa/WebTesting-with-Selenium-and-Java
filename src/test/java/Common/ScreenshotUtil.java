package Common;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtil extends DemoBlaze{

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            logger.info("Driver will take a screenshot.");

            try {
                logger.info("Driver will check if alert is present.");
                driver.switchTo().alert().accept();
            } catch (UnhandledAlertException e) {
            }

            logger.info("Driver will take screenshot.");
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            logger.info("We will create time stamp when screenshot happened.");
            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
            File destination = new File("screenshots/" + screenshotName + "_" + timeStamp + ".png");

            if (!destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs();
            }
            FileUtils.writeByteArrayToFile(destination, screenshotBytes);

        } catch (IOException e) {
            logger.error("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
