package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.CreateBucketRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CreateBucketTest extends BaseTest {

    @Test
    void creating_new_valid_bucket_should_return_specified_response() {
        loginAs(Roles.ADMIN);

        CreateBucketRequest createBucketRequest = CreateBucketRequest.generateValidBucketCreationRequest();
        Response response = skyNestBackendClient.createBucket(createBucketRequest);
        response.then().statusCode(SC_OK);

        BucketResponse createBucketResponse = response.as(BucketResponse.class);

        assertNotNull(createBucketResponse.getBucketId());
        assertEquals(createBucketResponse.getName(),createBucketRequest.getName());
        assertEquals(createBucketResponse.getDescription(), createBucketRequest.getDescription());
    }

    @Test(dataProvider = "invalidBucketData")
    void creating_new_invalid_bucket_should_return_specified_response(CreateBucketRequest createBucketRequest) {
        loginAs(Roles.ADMIN);
        Response response = skyNestBackendClient.createBucket(createBucketRequest);
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @DataProvider(name = "invalidBucketData")
    public Object[][] getInvalidBucketData() {
        return new Object[][] {
                new Object[]{ CreateBucketRequest.generateValidBucketCreationRequest().withName("").withDescription("")
                }
        };
    }
}