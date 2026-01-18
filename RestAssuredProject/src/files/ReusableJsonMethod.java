package files;

import io.restassured.path.json.JsonPath;

public class ReusableJsonMethod {
	
	public static JsonPath rawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
		
	}//End of rawToJson

}//End of ReusableJsonMethod
