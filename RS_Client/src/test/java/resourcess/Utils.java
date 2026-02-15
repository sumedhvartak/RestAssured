package resourcess;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

		RequestSpecification reqSpec;
		ResponseSpecification resSpec;
		
		public RequestSpecification requestSpecification() {
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "value")
					.setContentType(ContentType.JSON).build();
			return reqSpec;	
		}//End of requestSpecification()
		
		
		public ResponseSpecification responseSpecification() {
			resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			return resSpec;
			
		}//End of responseSpecification()

}
