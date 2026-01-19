import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableJsonMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BookData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String responsePost = given().log().all().header("Content-Type", "application/json").
		body("{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "").
		when().post("/Library/Addbook.php").
		then().extract().response().asString();
		
		JsonPath js = ReusableJsonMethod.rawToJson(responsePost);
		System.out.println("ID: " + js.getString("ID"));
		
	}//End of addBook	
		
	@DataProvider(name="BookData")
	public Object[][] getData() {
		return new Object[][]{{"bcd6755556455", "227"}, {"bcd67555453455", "227"}};
			
	}//End of BookData
		

}//End of DynamicJson
