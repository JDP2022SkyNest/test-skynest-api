package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.CreateBucketRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CreateBucketTest extends BaseTest {

    @Test(dataProvider = "BucketData")
    void creating_new_bucket_should_return_specified_response(CreateBucketRequest createBucketRequest) {
        loginAs(Roles.ADMIN);
        Response response = skyNestBackendClient.createBucket(createBucketRequest);
        response.then().statusCode(SC_OK);

        BucketResponse createBucketResponse = response.as(BucketResponse.class);

        assertNotNull(createBucketResponse.getBucketId());
        assertEquals(createBucketResponse.getName(), createBucketRequest.getName());
        assertEquals(createBucketResponse.getDescription(), createBucketRequest.getDescription());

    }

    @DataProvider(name = "BucketData")
    public Object[][] getBucketData() {
        return new Object[][]{
                new Object[]{CreateBucketRequest.generateValidBucketCreationRequest()}};
    }

}
