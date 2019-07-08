package file;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReuseableMethods {
	
	public static XmlPath rawToXML(Response resp)
	{
		XmlPath path = new XmlPath(resp.asString());
		return path;
		
	}
	
	
	public static JsonPath rawToJSON(Response resp)
	{
		JsonPath path = new JsonPath(resp.asString());
		return path;
		
	}

}
