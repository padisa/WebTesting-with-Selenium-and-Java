import Common.DemoBlaze;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;


public class ApiTests extends ApiRequests {
    URL api_Url;
    String signUp_url_string = getProperty("api_Url_Post_Sign_Up","Configurations/Config.properties");
    String getProduct_url_string = getProperty("api_Url_Get_Product","Configurations/Config.properties");
    HttpURLConnection connection;
    String username, password;

    @BeforeTest
    public void setUp(){
        username = DemoBlaze.generateRandomUsername();
        password = DemoBlaze.generatePassword();
    }

    @Test (priority = 0)
    public void getProductTest(){
        try {
            api_Url = defineApiUrl(getProduct_url_string);
            connection = defineElements("GET");
            executeRequest("GET", username, password);
            checkIfResponseIsValid("GET");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 1)
    public void postSignUpTest(){
        try {
            api_Url = defineApiUrl(signUp_url_string);
            connection = defineElements("POST");
            executeRequest("POST", username, password);
            checkIfResponseIsValid("POST");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
