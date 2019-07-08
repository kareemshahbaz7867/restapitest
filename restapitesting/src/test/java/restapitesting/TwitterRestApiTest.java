package restapitesting;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TwitterRestApiTest {
	
	String consumerKey= "M236GcyNeGwD2khuwVISEODRF";
	String consumerSecret="1sGMgEU2mnDNxLLrZdmNEFXp9uzAhVdM0pdHqIbESwHr9czYIb";
	String accessToken="2247476042-F62YCGDOEAVgBEZm74qxV9ZTliDQwi72f2I9uai";
	String secretToken="Uq9s5xWuuLmupR6CO9e9BpadGOTbjsp0qfwfPAsCx6YqK";
	
	
	@Test
	public void getLatestTweets()
	{
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response resp = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken).queryParam("count", "2").
		when().get("/home_timeline.json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.extract().response();
		
		System.out.println(resp.asString());
		
	}
	
	
	@Test
	public void postTweet()
	{
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response resp = given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken).queryParam("status", "helloworld").
				when().post("/update.json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();
				
		
	}

}
