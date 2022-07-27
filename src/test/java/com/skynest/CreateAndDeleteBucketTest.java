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

public class CreateAndDeleteBucketTest extends BaseTest {
    protected BucketResponse createdBucketResponse;

    @Test
    void logged_user_should_be_able_to_create_bucket_with_valid_inputs() {
        loginAs(Roles.ADMIN);

        CreateBucketRequest createBucketRequest = CreateBucketRequest.generateValidBucketCreationRequest();
        Response response = skyNestBackendClient.createBucket(createBucketRequest);
        response.then().statusCode(SC_OK);

        BucketResponse createBucketResponse = response.as(BucketResponse.class);

        createdBucketResponse = createBucketResponse;

        assertNotNull(createBucketResponse.getBucketId());
        assertEquals(createBucketResponse.getName(), createBucketRequest.getName());
        assertEquals(createBucketResponse.getDescription(), createBucketRequest.getDescription());
    }

    @Test(dependsOnMethods = "logged_user_should_be_able_to_create_bucket_with_valid_inputs")
    void user_should_be_able_to_delete_created_bucket() {
        skyNestBackendClient.deleteBucket(createdBucketResponse.getBucketId()).then().statusCode(SC_OK);
    }

    @Test(dataProvider = "invalidBucketData")
    void logged_user_should_not_be_able_to_create_bucket_with_invalid_data(CreateBucketRequest createBucketRequest) {
        loginAs(Roles.ADMIN);
        Response response = skyNestBackendClient.createBucket(createBucketRequest);
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @DataProvider(name = "invalidBucketData")
    public Object[][] getInvalidBucketData() {
        return new Object[][]{
                new Object[]{CreateBucketRequest.generateValidBucketCreationRequest().withName("")},
                {CreateBucketRequest.generateValidBucketCreationRequest().withDescription("")},
                {CreateBucketRequest.generateValidBucketCreationRequest().withName(null)},
                {CreateBucketRequest.generateValidBucketCreationRequest().withDescription(null)}
        };
    }
}