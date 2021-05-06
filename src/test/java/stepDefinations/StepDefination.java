package stepDefinations;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.junit.Assert;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ResourceEnum;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefination extends Utils{
	
	RequestSpecification reqf;
	ResponseSpecification resf= new ResponseSpecBuilder().expectStatusCode(200).expectHeader("server","Apache/2.4.18 (Ubuntu)").build();
	Response res;
	public static String placeID;
		
		@Given("Add place playload with {string} {string} {string}")
		public void add_place_playload_with(String name, String lan, String add) throws IOException {
			 reqf= given().spec(requsteSpecification()).body(TestDataBuild.AddPayload(name,lan,add));				
			 												  					 									  
		}

		@When("user calls {string} with {string} Http request")
		public void user_calls_with_http_request(String resource, String method) {
			if(method.equals("Post"))
				res=reqf.when().post(ResourceEnum.valueOf(resource).getResource())
						.then().spec(resf)
							.extract().response();
			else if (method.equals("Get"))
				res=reqf.when().get(ResourceEnum.valueOf(resource).getResource())
				.then().spec(resf)
					.extract().response();						
				
		}
		
		@Then("the API call got success with status code {int}")
		public void the_api_call_got_success_with_status_code(Integer int1) {
			Assert.assertEquals(res.getStatusCode(),200);
			System.out.println(res.asString());
		}
		
		@Then("{string} in response body is {string}")
		public void in_response_body_is(String string, String string2) 
		{
			
			  JsonPath js = new JsonPath(res.asString());
			  Assert.assertEquals(js.getString(string),string2);

			    		    
		}
		
		@Then("verify place_ID created with {string} using {string}")
		public void verify_place_id_created_with_using(String expectedName, String resource) throws IOException 
		{
			placeID=getJsonValue(res, "place_id");
			reqf= given().spec(requsteSpecification()).queryParam("place_id",placeID);
		   user_calls_with_http_request(resource, "Get");		   
		   Assert.assertEquals(getJsonValue(res, "name"),expectedName );
		}

		@Given("deletePlace payload.")
		public void delete_place_payload() throws IOException {
		    reqf=given().spec(requsteSpecification()).body(TestDataBuild.deletePayload(placeID));
		   
		}


	
	
}
