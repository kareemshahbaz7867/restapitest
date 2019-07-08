package restapitesting;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {
	
	@Test
	public void get()
	{
		baseURI = "http://216.10.245.166";
	//uri = http://216.10.245.166/maps/api/place/add/xml?key= qaclick123
		
		
	//example 1	->get request
//		  given().param("location", "qaclick123"). when().post("/maps/api/place/add/xml").
//		  then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
//		  .header("responseheader", "value"). and().body("response[0].detail.test",
//		  equalTo("helloworld"));
		 
		 
	//example 2	
		//get("http://localhost:8080/users").then().body("name", hasItems("Abdul", "Safoora"));
	}

}
