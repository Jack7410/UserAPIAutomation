 package api.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payload.UserPayload;
import io.restassured.response.Response;

public class SampleTests {
	
	Faker fake=new Faker();
	UserPayload payload= new UserPayload();
	
	@BeforeTest
	void setupData() {
		payload.setId(fake.number().numberBetween(1, 500));
		payload.setUsername(fake.name().username());
		payload.setFirstname(fake.name().firstName());
		payload.setLastname(fake.name().lastName());
		payload.setEmail(fake.internet().emailAddress());
		payload.setPassword(fake.internet().password());
		payload.setPhoneno(fake.phoneNumber().cellPhone());
		payload.setUserStatus(fake.number().numberBetween(1, 2));
	}
	
	@Test(priority = 1)
	void postData() {
		Response res=UserEndpoint.post_Data(payload);
		
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().all();
	}
	
	
	@Test(priority = 2)
	void updateData() {
		
		payload.setId(fake.number().numberBetween(1, 500));
		payload.setUsername(payload.getUsername());
		payload.setFirstname("Mike");
		payload.setLastname(fake.name().lastName());
		payload.setEmail(fake.internet().emailAddress());
		payload.setPassword(fake.internet().password());
		payload.setPhoneno(fake.phoneNumber().cellPhone());
		payload.setUserStatus(fake.number().numberBetween(1, 2));
		
		Response res=UserEndpoint.update_user(payload.getUsername(), payload);
		
	
		
		System.out.println(res.statusCode());
		
		
		Response resget=UserEndpoint.get_user(payload.getUsername());
		resget.then().log().body();
		
		
	}
	
	@Test(priority = 3)
	void getData() {
		Response res=UserEndpoint.get_user(payload.getUsername());
		
		res.then().log().all();
	}
	
	@Test(priority = 4)
	void deleteData() {
		Response res=UserEndpoint.delete_user(payload.getUsername());
		
		System.out.println(res.getStatusCode());
		
		Response res2=UserEndpoint.get_user(payload.getUsername());
		
		res2.then().log().all();
	}
	

}
