package resourcess;

import java.util.ArrayList;
import java.util.List;

import pojos.AddPlace;
import pojos.Location;


public class TestDataBuild {
	
public AddPlace addPlacePayload() {
		
		AddPlace a = new AddPlace();
		a.setAccuracy("50");
		a.setAddress("Frontline house");
		a.setName("(+91) 983 893 3937");
		a.setPhone_number("29, side layout, cohen 09");
		List<String> li = new ArrayList<String>();
		li.add("shoe park");
		li.add("shop");
		a.setTypes(li);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		return a;
		
	}//End of addPlacePayload

}
