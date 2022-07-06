package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.config.properties.PropertiesReader;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;

public class BaseTest {
    public static final String TARGET_DOMAIN = "targetDomain";
    protected SkyNestBackendClient skyNestBackendClient;

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() throws URISyntaxException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        String targetDomain = System.getProperty(TARGET_DOMAIN, propertiesReader.getProperty(TARGET_DOMAIN));
        skyNestBackendClient = new SkyNestBackendClient(targetDomain);
    }

}
