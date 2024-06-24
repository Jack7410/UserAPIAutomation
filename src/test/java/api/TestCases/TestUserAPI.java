package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payload.UserPayload;
import io.restassured.response.Response;

public class TestUserAPI {

	Faker fake;
	UserEndpoint endpoints;
	UserPayload payload;
	
	@BeforeClass
	void setupData() {
		fake=new Faker();
		payload=new UserPayload();
		
		
		payload.setId(fake.number().numberBetween(1, 100));
		payload.setUsername(fake.name().username());
		payload.setFirstname(fake.name().firstName());
		payload.setLastname(fake.name().lastName());
		payload.setPassword(fake.internet().password());
		payload.setEmail(fake.internet().emailAddress());
		payload.setPhoneno(fake.phoneNumber().cellPhone());
		payload.setUserStatus(fake.number().numberBetween(1, 2));
		
	}
	
	@Test(priority = 1)
	void testPostUser() {
		Response res=endpoints.post_Data(payload);
		
		res.then().statusCode(200);
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().body();
	}
	
	@Test(priority = 2)
	void testGetUser() {
		Response res=endpoints.get_user(payload.getUsername());
		
		
		Assert.assertEquals(res.getBody().jsonPath().get("username"), payload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("The userName: "+payload.getUsername());
		res.then().log().all();
	}
	
	
	
	@Test(priority = 3)
	void testUpdateUser() {
		
		
		payload.setId(fake.number().numberBetween(1, 100));
		payload.setUsername(payload.getUsername());
		payload.setFirstname("Sakshi");
		payload.setLastname("Rout");
		payload.setPassword(fake.internet().password());
		payload.setEmail(fake.internet().emailAddress());
		payload.setPhoneno(fake.phoneNumber().cellPhone());
		payload.setUserStatus(fake.number().numberBetween(1, 2));
		
		Response res=endpoints.update_user(payload.getUsername(), payload);
		
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().all();
		
	}
	
	
	@Test(priority=4)
	void testDeleteUser() {
		Response res=endpoints.delete_user(payload.getUsername());
		Assert.assertEquals(res.getStatusCode(),404);
		res.then().log().all();
	}
}
