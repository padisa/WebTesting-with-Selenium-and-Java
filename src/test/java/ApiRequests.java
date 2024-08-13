import Common.DemoBlaze;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpOptions;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiRequests extends DemoBlaze {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ApiRequests.class.getName());
    public URL url;
    public HttpURLConnection connection = null;
    HttpPost postRequest;
    HttpGet getRequest;
    HttpOptions optionsRequest;


    public URL defineApiUrl(String apiUrl) throws MalformedURLException {
        logger.info("Define the url of API.");
        url = new URL(apiUrl);
        return url;
    }
    public HttpURLConnection defineElements(String method) {
        try{
            logger.info("Open connection for defined url.");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            if(method.equals("POST")) {
                logger.info("Set up the request properties for POST.");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void executeRequest(String method, String usernameValue, String passwordValue){
        if ("POST".equalsIgnoreCase(method)) {
            String jsonBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", usernameValue, passwordValue);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void checkIfResponseIsValid(String method) {
        try {
            int responseCode = connection.getResponseCode();
            logger.info(method + " Response Code: " + responseCode);

            logger.info("Check if the " + method +" request was successful");
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println(method + " request worked successfully.");
            } else {
                System.out.println(method +" request failed.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
