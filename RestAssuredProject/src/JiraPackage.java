import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import files.Payload;

public class JiraPackage {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://sumedh-vartak.atlassian.net";
		String bugCreatedResponse = given().log().all()
		.header("Authorization", "Basic c3VtZWRodmFydGFrMTk5N0BnbWFpbC5jb206QVRBVFQzeEZmR0YwWlBaS3BGSzVsX05kOTg1X2FfazF1RjFMVEltSEJnblBKWEdOZ0pvVElBNDlGTWd2VnhOZUFkSGE3Zl96NjRJb0FPVWVSNUtyZzBCQ1FYQVhRNEhXSE0zOEdodkVodzVJSm1lSXZDVEQycHFETFdiY0JEdUw0SW16TXpDam8zTS1MS0dnSGVQdlFFbVJPU0VJVUVpdmFBTjNSQjVsdWR4dHlhMjc2NGFEOWNjPTRDMDkwMzVE")
		.header("Content-Type", "application/json")
		.header("Accept", "application/json")
		.body(Payload.createBugJira())
		.when().post("rest/api/3/issue")
		.then().assertThat().statusCode(201).extract().response().asString();
		System.out.println(bugCreatedResponse);
		
	}//End of main method

}//End of JiraPackage
