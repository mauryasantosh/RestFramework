package resources;

import java.util.Arrays;

import Pojo.Location;
import Pojo.Place;

public class TestDataBuild 
{
public static Place AddPayload(String name, String lan, String add)
{
	Location location = new Location();
	location.setLat("-38.383494");
	location.setLng("33.427362");
	
	Place place = new Place();
	place.setLocation(location);
	place.setName(name);
	place.setAccuracy(50);
	place.setAddress(add);
	place.setLanguage(lan);	
	place.setPhone_number("(+91) 983 893 3937");
	place.setWebsite("http://rahulshettyacademy.com");
	String[] types= {"shop","gym"};
	place.setTypes(Arrays.asList(types));
	return place; 
	
	}

public static String deletePayload(String placeid)
{
return "{\r\n"
		+ "    \"place_id\":\""+placeid+"\"\r\n"
		+ "}";
}
}

