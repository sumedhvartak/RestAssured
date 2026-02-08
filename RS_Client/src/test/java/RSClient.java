import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.nio.file.Files;
import java.nio.file.Paths;

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
	
	@Test
	public void createProduct() {
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//In header section token needs to be provided
		given().header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTFhMDhiODUwMDhmNmE5MDkyMzQ1ZWUiLCJ1c2VyRW1haWwiOiJyYWptYW5lQGdtYWlsLmNvbSIsInVzZXJNb2JpbGUiOjg5NzIzNDIxMzMsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3NzA1NjAyMjUsImV4cCI6MTgwMjExNzgyNX0.bYeJdm01328LCPA7DhuE6pkDNsDTv5-fpba4vJqrM3A").
		formParam("productName", "Iphone").
		formParam("productAddedBy", userID). //Here UserID needs to be added
		formParam("productCategory", "Electronics").
		formParam("productSubCategory", "Mobiles").
		formParam("productPrice", "1000").
		formParam("productDescription", "Apples Coop.Ltd").
		formParam("productImage", "Generic").
//		formParam("productImage", Files.readAllBytes(((RestAssured) Paths.get("C:\\Users\\Sumedh-PC\\OneDrive\\Pictures\\Screenshots\\Screenshot 2026-01-09 013409.png"))
		when().post("api/ecom/product/add-product"); 
		
	}//End of createProduct()

}//End of RSClient
