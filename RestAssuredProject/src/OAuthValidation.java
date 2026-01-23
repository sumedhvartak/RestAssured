import org.testng.annotations.Test;
import files.ReusableJsonMethod;
import io.restassured.path.json.JsonPath;
import pojo.GetCourses;

import static org.hamcrest.Matchers.*; 
import static io.restassured.RestAssured.*;

public class OAuthValidation {
	
	@Test
	public void performOAuth() {
		
		String responseOAuth = given().log().all().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().extract().response().asString();
		
		JsonPath js = ReusableJsonMethod.rawToJson(responseOAuth);
		String accessToken = js.getString("access_token");
		System.out.println("Access Token: " + accessToken);
		
		GetCourses gc = given().queryParam("access_token", accessToken).
		when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
		
		System.out.println("Get Courses objecy: " + gc);
		
		
	}//End of performOAuth

}//End of class
