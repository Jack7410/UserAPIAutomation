package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoint {
	
	public static Response post_Data(UserPayload payload) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when().post(Routes.post_Url);
	}
	
	public static Response get_user(String username) {
		return given().contentType("application/json").pathParam("username", username).when().get(Routes.get_Url);
	}
	
	public static Response update_user(String username,UserPayload payload) {
		return given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", username).when().patch(Routes.put_Url);
	}
	
	public static Response delete_user(String username) {
		return given().pathParam("username", username).when().delete(Routes.delete_Url);
	}

}
