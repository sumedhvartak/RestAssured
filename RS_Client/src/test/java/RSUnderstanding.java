import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Pojo.LoginRequest;

import static io.restassured.RestAssured.*;
public class RSUnderstanding {
	
	public static void main(String[] args) throws IOException {
		
		
		//Passing request payload in body section as Traditonal Approach
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().header("Content-Type", "Application/Json").
		body("{\r\n"
				+ "    \"userEmail\": \"rajmane@gmail.com\",\r\n"
				+ "    \"userPassword\": \"Password@123\"\r\n"
				+ "}").
		when().post("api/ecom/auth/login"). 
		then().body("message", equalTo("Login Successfully")).assertThat().statusCode(200).extract().response().asString();
		
		//Passing request payload in body section from external class Method
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().header("Content-Type", "Application/Json").
		body(ExternalClass.login()).
		when().post("api/ecom/auth/login"). 
		then().log().all().body("message", equalTo("Login Successfully")).assertThat().statusCode(200).extract().response().asString();
		
		//Passing request payload in body section  an external File
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().header("Content-Type", "Application/Json").
		body(new String(Files.readAllBytes(Paths.get("D:\\NewKnowledge\\RestAssured\\RestAssuredWorkspace\\RS_Client\\src\\test\\java\\Payload.json")))).
		when().post("api/ecom/auth/login"). 
		then().log().all().body("message", equalTo("Login Successfully")).assertThat().statusCode(200).extract().response().asString();
		
		//Passing request payload in body section as Serialization
		LoginRequest lg  = new LoginRequest();
		lg.setUserEmail("rajmane@gmail.com");
		lg.setUserPassword("Password@123");
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().header("Content-Type", "Application/Json").
		body(new String(Files.readAllBytes(Paths.get("D:\\NewKnowledge\\RestAssured\\RestAssuredWorkspace\\RS_Client\\src\\test\\java\\Payload.json")))).
		when().post("api/ecom/auth/login"). 
		then().log().all().body("message", equalTo("Login Successfully")).assertThat().statusCode(200).extract().response().asString();
		
	}//End of main method



}//End of RSUnderstanding
