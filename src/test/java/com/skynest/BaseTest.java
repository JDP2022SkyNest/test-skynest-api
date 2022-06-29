package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.config.properties.PropertiesReader;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;

public class BaseTest {
    protected SkyNestBackendClient skyNestBackendClient;
    private String targetDomain;

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() throws URISyntaxException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        targetDomain = System.getProperty("targetDomain", propertiesReader.getProperty("targetDomain"));
        skyNestBackendClient = new SkyNestBackendClient(targetDomain);
    }

}
