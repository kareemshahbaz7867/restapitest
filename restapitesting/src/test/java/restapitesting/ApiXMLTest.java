package restapitesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import file.Payload;
import file.ReuseableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ApiXMLTest {
	
		Properties prop;
		
		@BeforeTest
		public void initialization() throws IOException
		{
			prop = new Properties();
			System.out.println();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\file\\env.properties");
		    prop.load(fis);
			
		}
		
		@Test
		public void post() throws IOException
		{
			//http://216.10.245.166/maps/api/place/add/json?key= qaclick123
			
			RestAssured.baseURI = prop.getProperty("baseURL");
			System.out.println(System.getProperty("user.dir"));
			given().queryParam("key", "qaclick123").body(Payload.postAddPlaceXmlBody()).
			when().post("/maps/api/place/add/xml").
			then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().body("response.status", equalTo("OK")).and().body("response.scope", equalTo("APP"));
			
		}
		
		
		//multiple API calls
		@Test
		public void addDeletePlace() throws IOException
		{
		
	RestAssured.baseURI = prop.getProperty("baseURL");
			
			Response addPlace = given().queryParam("key", prop.getProperty("key")).body(Payload.postAddPlaceXmlBody()).
			when().post("/maps/api/place/add/json").
			then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().body("response.status", equalTo("OK")).and().body("response.scope", equalTo("APP")).
			extract().response();
		
			String responseString = addPlace.asString();
			
			System.out.println(responseString);
			
			//string is converted to json
			XmlPath xml = ReuseableMethods.rawToXML(addPlace);
			String placeid_value = xml.get("place_id");
			
			given().queryParam("key", prop.getProperty("key")).body("<"+
					"\"place_id\":\""+placeid_value+"\""+				
					"}").
			when().post("/maps/api/place/delete/json").
			then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().body("response.status",equalTo("OK"));
			
			
		}
		



}
