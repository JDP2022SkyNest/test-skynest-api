package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.config.properties.PropertiesReader;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;

public class BaseTest {
    protected SkyNestBackendClient skyNestBackendClient;

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() throws URISyntaxException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        String targetDomain = System.getProperty("targetDomain", propertiesReader.getProperty("targetDomain"));
        skyNestBackendClient = new SkyNestBackendClient(targetDomain);
    }

}
