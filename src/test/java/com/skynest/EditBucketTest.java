package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.EditBucketRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class EditBucketTest extends BucketBaseTest {

    @Test(dataProvider = "BucketData")
    void logged_admin_should_be_able_to_edit_specific_bucket(EditBucketRequest editBucketRequest) {
        Response response = skyNestBackendClient.editBucket(editBucketRequest, createdBucketId);
        response.then().statusCode(SC_OK);

        BucketResponse editBucketResponse = response.as(BucketResponse.class);

        assertEquals(editBucketResponse.getName(), editBucketRequest.getName());
        assertEquals(editBucketResponse.getDescription(), editBucketRequest.getDescription());
        assertEquals(editBucketResponse.getIsPublic(), editBucketRequest.getIsPublic());
    }

    @DataProvider(name = "BucketData")
    public Object[][] getBucketData() {
        return new Object[][]{
                new Object[]{EditBucketRequest.generateValidEditBucketRequest()}
        };
    }

}
