package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import api.payload.UserPayload;
import io.restassured.response.Response;


public class TestBulkPostData {
	
	
	UserPayload payload;
	@Test(dataProvider = "ReadUserData",dataProviderClass = DataReader.class)
	void validateBulkPost(String id,String username,String firstName,String lastName,String email,String password,String phonenumber,String userStatus) {
		payload=new UserPayload();
		payload.setId(Integer.valueOf(id));
		payload.setUsername(username);
		payload.setFirstname(firstName);
		payload.setLastname(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhoneno(phonenumber);
		payload.setUserStatus(Integer.valueOf(userStatus));
		
		Response res=UserEndpoint.post_Data(payload);
		
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().all();
	

}
}
