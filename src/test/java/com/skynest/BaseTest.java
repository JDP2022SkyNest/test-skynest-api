package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;

public class BaseTest {
    //Protected access modifier because it's used in all child classes
    protected SkyNestBackendClient skyNestBackendClient;

    @BeforeSuite
    public void setUp() throws URISyntaxException {
        skyNestBackendClient = new SkyNestBackendClient();
    }
}
