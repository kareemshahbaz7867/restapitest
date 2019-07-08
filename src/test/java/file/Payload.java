package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Payload {
	
	public static String postAddPlaceXmlBody() throws IOException
	{
		
		return generateStringFromResource(System.getProperty("user.dir")+"\\src\\test\\java\\file\\postaddplace.xml");
		
		//return new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\test\\java\\file\\postaddplace.xml")));
		
	}
	
	public static String postAddPlaceJsonBody()
	{
		
		String payload ="{"+
				 "\"location\":{"+
				   "\"lat\" : -38.383494,"+
				   "\"lng\" : 33.427362 },"+
				  "\"accuracy\":50,"+
				 "\"name\":\"Frontline house\","+
				 "\"phone_number\":\"(+91) 983 893 3937\","+
				"\"address\" : \"29, side layout, cohen 09\","+
				"\"types\": [\"shoe park\",\"shop\"],"+
				"\"website\" : \"http://google.com\","+
				"\"language\" : \"French-IN\""+
				"}";
		
		return payload;
		
	}
	
	
	public static String postDeleteJsonBody(String placeid_value)
	{
		return "{"+
				"\"place_id\":\""+placeid_value+"\""+				
				"}";
	}
	
	
	public static String addBook(String isbn, String aisle)
	{
		String value = "{\n" +

		"\n" +

		"\"name\":\"Learn Appium Automation with Java\",\n" +

		"\"isbn\":\""+isbn+"\",\n" +

		"\"aisle\":\""+aisle+"\",\n" +

		"\"author\":\"John foe\"\n" +

		"}\n" +

		" \n" +

		"";



		System.err.println("Value :" + value);



		return value;



		}


	public static String jiraAuthenticate(String username , String password)
	{
		
		String request = "{"+
						  "\"username\" :\""+username+"\","
						  +"\"password\": \""+password+"\""
						  +"}";
		
		return request;
	}
	
	
	public static String jiraCreateIssue(String projectKey,String summary, String desc )
	{
				
		String request1 = "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \""+projectKey+"\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \""+summary+"\",\r\n" + 
				"       \"description\": \""+desc+"\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"        \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}";
		return request1;
	}
	  public static String generateStringFromResource(String path) throws
	  IOException { return new String(Files.readAllBytes(Paths.get(path))); }
	 

}
