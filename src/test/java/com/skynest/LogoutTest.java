package com.skynest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.clients.SkyNestBackendClient;
import com.skynest.files.LogoutRequest;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class LogoutTest extends BaseTest {
    private SkyNestBackendClient skyNestBackendClient = new SkyNestBackendClient();

    public LogoutTest() throws URISyntaxException {
    }

    @Test
    void valid_logout_test() throws JsonProcessingException {
        LogoutRequest header = new LogoutRequest();
        header.
        //Response response = skyNestBackendClient.logout();

    }
}
