package com.skynest.tests.clients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.ResponseParserRegistrar;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.internal.TestSpecificationImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class BaseClient {


    private URI baseUri = null;

    public static void setAuthToken(String authToken) {
        BaseClient.authToken = authToken;
    }

    private static String authToken;
    private String initialPath = "";
    private RestAssuredConfig restAssuredConfig;
    private final ResponseParserRegistrar responseParserRegistrar = new ResponseParserRegistrar();


    public BaseClient(String baseUri) throws URISyntaxException {
        this.baseUri = new URI(baseUri);
        restAssuredConfig = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((aClass, s) -> new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false))).logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));
    }

    public RequestSpecification requestMaker() {
        LogRepository logRepository = new LogRepository();
        RequestSpecification requestSpec = new TestSpecificationImpl(
                new RequestSpecificationImpl(
                        baseUri.toString(),
                        baseUri.getPort(),
                        initialPath,
                        RestAssured.DEFAULT_AUTH,
                        new ArrayList<>(),
                        null,
                        RestAssured.DEFAULT_URL_ENCODING_ENABLED,
                        restAssuredConfig,
                        logRepository,
                        null,
                        true),

                new ResponseSpecificationImpl(
                        RestAssured.DEFAULT_BODY_ROOT_PATH,
                        null,
                        responseParserRegistrar,
                        restAssuredConfig,
                        logRepository))
                .getRequestSpecification();

//        setLoggingFilters(requestSpec);
//        requestSpec.queryParam("_csrf", getCookies().get("XSRF-TOKEN"));
//        requestSpec.cookies(getCookies());

        if (BaseClient.authToken != null) {
            requestSpec.header("Authorization", authToken);
        }
        requestSpec.contentType(ContentType.JSON);
        requestSpec
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        return requestSpec;
    }
}
