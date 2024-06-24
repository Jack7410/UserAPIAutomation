package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import io.restassured.response.Response;

public class TestBulkDeleteData {
	
	@Test(dataProvider = "UserName",dataProviderClass = DataReader.class)
	void testDeleteUser(String username) {
		Response res=UserEndpoint.delete_user(username);
		
		System.out.println("****************"+username);
		Assert.assertEquals(res.getStatusCode(), 404);
		
		res.then().log().all();
	}

}
