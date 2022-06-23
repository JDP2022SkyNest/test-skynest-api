import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.tests.clients.SkyNestBackendClient;
import com.skynest.tests.files.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class LoginTest extends BaseTest{
    private SkyNestBackendClient skyNestBackendClient = new SkyNestBackendClient();

    public LoginTest() throws URISyntaxException {
    }

    @Test
    void newTest() throws JsonProcessingException {
        LoginRequest body = new LoginRequest();
        body.setEmail("hristina.zaharieva@htecgroup.com");
        body.setPassword("Hris8833");
        Response response = skyNestBackendClient.login(body);
        //skyNestBackendClient.get();
        //skyNestBackendClient.login(body);
    }
}
