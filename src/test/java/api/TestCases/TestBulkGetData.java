package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import io.restassured.response.Response;

public class TestBulkGetData {
	
	
	
	@Test(dataProvider = "UserName",dataProviderClass = DataReader.class)
	void testBulkGetData(String username) {
		Response res=UserEndpoint.get_user(username);
		
		System.out.println("***************"+username);
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getBody().jsonPath().get("username"), username);
		
		System.out.println("\n"+res.then().log().body());
	}

}
