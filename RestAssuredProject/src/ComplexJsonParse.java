import org.testng.Assert;

import files.Payload;
import files.ReusableJsonMethod;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		JsonPath js = ReusableJsonMethod.rawToJson(Payload.coursesData());
		//Print number of courses returned By API
		System.out.println("Number of courses are: " + js.get("courses.size()"));
		
		//Print Purchase Amount
		System.out.println("Total Purchase Amount: " + js.get("dashboard.purchaseAmount"));
		
		//Print Title of first course
		System.out.println("First Course Title: " + js.getString("courses[0].title"));
		// or 
		System.out.println("First Course Title: " + js.get("courses[0].title"));
		
		//Print all course Title and their respective prices
		int sum = 0;
		for(int i = 0; i < js.getInt("courses.size()"); i++) {
			System.out.println("Title of course: " + js.getString("courses[" + i + "].title") + " and prices are: " + js.getString("courses[ " + i + "].price"));
			
			//Copies sold by RPA are
			if(js.getString("courses[" + i + "].title").equals("RPA")) {
				System.out.println("Number of copies sold by RPA are " + js.getString("courses[" + i + "].copies"));
			}//End of If
			sum = sum + (js.getInt("courses[ " + i + "].price") * js.getInt("courses[ " + i + "].copies"));
		}//End of for
		
		//Sum of all copies and their prices
		System.out.println("Sum of all courses prices after calculation: " + sum);
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
		
		
	}//End of main method

}//End of ComplexJsonParse
