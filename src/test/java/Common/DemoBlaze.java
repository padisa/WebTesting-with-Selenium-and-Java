package Common;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class DemoBlaze {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DemoBlaze.class.getName());

    public static String url_Home_Page = getProperty("url_Home_Page","Configurations/Config.properties");
    public static String xpath_Product_Store = getProperty("xpath_Product_Store","Configurations/Config.properties");
    public static String xpath_Home = getProperty("xpath_Home","Configurations/Config.properties");
    public static int waitTimeSeconds = Integer.parseInt(getProperty("wait_time_seconds", "Configurations/Config.properties"));
    public static String xpath_Sign_Up_modal = getProperty("xpath_Sign_Up_modal","Configurations/Config.properties");
    public static String xpath_Sign_Up_modal_label = getProperty("xpath_Sign_Up_modal_label","Configurations/Config.properties");
    public static String xpath_Username_input = getProperty("xpath_Username_input","Configurations/Config.properties");
    public static String xpath_Password_input = getProperty("xpath_Password_input","Configurations/Config.properties");
    public static String xpath_Sign_Up_button = getProperty("xpath_Sign_Up_button","Configurations/Config.properties");
    public String page;

    public static Properties properties;

    public static void readPath(String fileName) {
        try {
            logger.info("Load properties from a specified resource file in classpath. Resource file is: " + fileName);
            properties = new Properties();
            InputStream inputStream = DemoBlaze.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Credentials properties not found.");
        }
    }

    public static String getProperty(String property, String fileName){
        readPath(fileName);
        logger.info("Load properties for property: " + property);
        return properties.getProperty(property);
    }

    public static void openHomePage(WebDriver driver) {
        driver.navigate().to(url_Home_Page);
    }

    public static void verifyHomePageIsOpened(WebDriver driver, WebDriverWait wait) {
        logger.info("Driver will validate that expected url is shown");
        String expectedUrl = "demoblaze";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl)) {
            logger.info("Expected URL for Home Page is shown.");
        } else {
            logger.info("We are not on right page. Please return on Home Page");
        }
        logger.info("Driver will check if Product Store is clickable.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Product_Store)));

        logger.info("Driver will check if Home is clickable.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Home)));
    }

    public static void openAndVerifySignUpModal(WebDriver driver, WebDriverWait wait){
        logger.info("Driver will check if Sign Up Button is clickable and then perform a click.");
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Sign_Up_modal)));
        signUpButton.click();

        logger.info("Driver will validate header of Sign Up modal.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Sign_Up_modal_label)));

        logger.info("Driver will check if Sign Up button is clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Sign_Up_button)));

    }

    public static void enterSignUpData(WebDriver driver, WebDriverWait wait, String username, String password){

        logger.info("We will generate random number to join in our username and password.");
        Random rand = new Random();
        int value = rand.nextInt(50);

        logger.info("Driver will check if input filed for Username is clickable.");
        WebElement userNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Username_input)));
        userNameInput.click();
        userNameInput.sendKeys(username + value);

        logger.info("Driver will check if input field for Password is clickable.");
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Password_input)));
        passwordInput.click();
        passwordInput.sendKeys(password + value);

        logger.info("Driver will click on Sign Up button.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_Sign_Up_button))).click();
    }

    public static void validateSuccessfulSignUp(WebDriver driver, WebDriverWait wait) {
        logger.info("Driver will check if alert is present.");
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        Assert.assertTrue(alertText.contains("Sign up successful."), "Alert text does not contain expected message.");

        logger.info("Driver will click to accept alert.");
        alert.accept();
    }

    public static String generateRandomUsername() {
        String username = "RandomUser";
        Random random = new Random();
        int value = random.nextInt(1000);
        username = username + value;
        return  username;
    }

    public static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String password = RandomStringUtils.random( 8, characters );
        return password;
    }
}
