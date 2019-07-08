package restapitesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import file.Payload;
import file.ReuseableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiJsonTest {
	
	Properties prop;
	private static Logger log = LogManager.getLogger(ApiJsonTest.class.getName());
	@BeforeTest
	public void initialization() throws IOException
	{
		prop = new Properties();
		System.out.println();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\file\\env.properties");
	    prop.load(fis);
		
	}
	
	@Test
	public void post()
	{
		//http://216.10.245.166/maps/api/place/add/json?key= qaclick123
		
		log.info("Host Information : "+prop.getProperty("baseURL"));
		RestAssured.baseURI = prop.getProperty("baseURL");
		System.out.println(System.getProperty("user.dir"));
		given().queryParam("key", "qaclick123").body(Payload.postAddPlaceJsonBody()).log().all().
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK")).and().body("scope", equalTo("APP")).log().all();
		
	}
	
	
	//multiple API calls
	@Test
	public void addDeletePlace()
	{
	
		
		log.info("Host Information : "+prop.getProperty("baseURL"));
RestAssured.baseURI = prop.getProperty("baseURL");
		
		Response addPlace = given().queryParam("key", prop.getProperty("key")).body(Payload.postAddPlaceJsonBody()).
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK")).and().body("scope", equalTo("APP")).
		extract().response();
		
		log.info("Response :"+addPlace.asString());
	
		JsonPath js = ReuseableMethods.rawToJSON(addPlace);
	
		String placeid_value = js.get("place_id");
		log.info("place id value "+placeid_value);
		
		given().queryParam("key", "qaclick123").body(Payload.postDeleteJsonBody(placeid_value)).
		when().post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK"));
		
		
		
		
	
	
	}
	
}
