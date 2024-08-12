import Common.DemoBlaze;
import Common.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.slf4j.Logger;

import java.time.Duration;


public class SignUpTest extends DemoBlaze {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SignUpTest.class.getName());
    private String username, password;
    private  WebDriver driver;
    private  WebDriverWait wait;
    DemoBlaze demoblaze = new DemoBlaze();

    @BeforeClass
    public  void setUpOfData() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(DemoBlaze.waitTimeSeconds));
        username = DemoBlaze.getProperty("DemoBlaze_Username", "Configurations/Credentials.properties");
        password = DemoBlaze.getProperty("DemoBlaze_Password", "Configurations/Credentials.properties");
    }

    @Test(priority = 1)
    public void openAndVerifyHomePage() {

        demoblaze.openHomePage(driver);
        demoblaze.verifyHomePageIsOpened(driver, wait);
    }

    @Test(priority = 2)
    public void openAndVerifySignUpModule() {
        demoblaze.openAndVerifySignUpModal(driver, wait);
    }

    @Test(priority = 3)
    public void enterSignUpData() {
        demoblaze.enterSignUpData(driver, wait, username, password);
    }

    @Test(priority = 4)
    public void validateSuccessfulMessage() {
        demoblaze.validateSuccessfulSignUp(driver, wait);
        DriverManager.quitDriver();
    }

}
