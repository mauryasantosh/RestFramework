package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils 
{

	public static RequestSpecification reqf;
	public RequestSpecification requsteSpecification() throws IOException
	{
		
		if(reqf==null)
		{
			PrintStream log = new PrintStream( new FileOutputStream("Logging.txt"));
			reqf=  new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123")
					.addHeader("Content-Type","application/json")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqf;
		}
		return reqf;
				
	}
	
	public static String  getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\mauryas\\eclipse-workspace\\RestFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonValue(Response response, String key)
	{
		JsonPath js = new JsonPath(response.asString());		
		return js.get(key);
	}
	
}
