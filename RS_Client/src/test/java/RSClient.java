import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import Pojo.LoginRequest;
import Pojo.LoginResponse;

public class RSClient {
	
	public String token;
	public String userID;
	public String message;
	
	@Test
	public void login() {
			
		LoginRequest login = new LoginRequest();
		login.setUserEmail("rajmane@gmail.com");
		login.setUserPassword("Password@123");
		
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		RequestSpecification RequestSpec = given().spec(reqSpec).body(login);
		
		LoginResponse loginResponse = RequestSpec.post("/api/ecom/auth/login").as(LoginResponse.class);
		
		token = loginResponse.getToken();
		userID = loginResponse.getUserId();
		message = loginResponse.getMessage();
		
		System.out.println("Login Response: " + loginResponse);
		System.out.println("Token: " + loginResponse.getToken());
		System.out.println("UserID: " + loginResponse.getUserId());
		System.out.println("Message: " + loginResponse.getMessage());
		
	}//End of login

}//End of RSClient
