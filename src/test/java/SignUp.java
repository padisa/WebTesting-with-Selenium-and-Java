import Common.DemoBlaze;
import Common.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.slf4j.Logger;

import java.time.Duration;


public class SignUp extends DemoBlaze {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SignUp.class.getName());
    private String username, password;
    private  WebDriver driver;
    private  WebDriverWait wait;


    @BeforeClass
    public  void setUpOfData() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(DemoBlaze.waitTimeSeconds));
        username = DemoBlaze.getProperty("DemoBlaze_Username", "Configurations/Credentials.properties");
        password = DemoBlaze.getProperty("DemoBlaze_Password", "Configurations/Credentials.properties");
    }

    @Test(priority = 1)
    public void openAndVerifyHomePage() {
        DemoBlaze.openHomePage(driver);
        DemoBlaze.verifyHomePageIsOpened(driver, wait);
    }

    @Test(priority = 2)
    public void openAndVerifySignUpModule() {
        DemoBlaze.openAndVerifySignUpModal(driver, wait);
    }

    @Test(priority = 3)
    public void enterSignUpData() {
        DemoBlaze.enterSignUpData(driver, wait, username, password);
    }

    @Test(priority = 4)
    public void validateSuccessfulMessage() {
        DemoBlaze.validateSuccessfulSignUp(driver, wait);
        DriverManager.quitDriver();
    }

}
