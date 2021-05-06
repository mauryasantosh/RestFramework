package stepDefinations;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {
@Before("@DeletePlace")
public void beforeDelete() throws IOException
{
	StepDefination stepdef= new StepDefination();
	if(StepDefination.placeID==null)
	{
		stepdef.add_place_playload_with("QA","Java","India");		
		stepdef.user_calls_with_http_request("AddPlaceAPI", "Post");
		stepdef.verify_place_id_created_with_using("QA", "GetPlaceAPI");
	}
}
}
