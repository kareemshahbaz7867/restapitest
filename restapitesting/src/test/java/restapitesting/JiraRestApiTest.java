package restapitesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import file.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraRestApiTest {
	
	@Test
	public void createJiraIssue()
	{
		
		RestAssured.baseURI="http://localhost:8099/";
		
		//authenticate into Jira and then the session id
		Response resp = given().header("Content-type","application/json").body(Payload.jiraAuthenticate("kareemshahbaz", "kareemshahbaz")).
		when().post("/rest/auth/1/session").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.extract().response();
		
		JsonPath path =  new JsonPath(resp.asString());
		String name = path.get("session.name");
		String value = path.get("session.value");
		
		System.out.println(name+"="+value);
		
		
		System.out.println(Payload.jiraCreateIssue("MYP", "my bug summary", "my bug description"));
		//now make a jira create issue call
		resp = given().header("Content-Type","application/json").header("Cookie",name+"="+value)
				.body(Payload.jiraCreateIssue("MYP", "my bug summary", "my bug description")).
		when().post("rest/api/2/issue").
		then().assertThat().statusCode(201).and().contentType(ContentType.JSON)
		.extract().response();
		
		path = new JsonPath(resp.asString());
		String id = path.get("id");
		String key = path.getString("key");
		
		//now create comments for the above created defect.
		//now make a jira create issue call
		//resource example : /rest/api/2/issue/MYP-2/comment
				resp = given().header("Content-Type","application/json").header("Cookie",name+"="+value).body("{\r\n" + 
						"    \"body\": \"This is a comment regarding the quality of the response.\"\r\n" + 
						"}").
				when().post("rest/api/2/issue/"+key+"/comment").
				then().assertThat().statusCode(201).and().contentType(ContentType.JSON)
				.extract().response();
		
		path = new JsonPath(resp.asString());
		String commentId = path.get("id");
		
		//delete the above comments made from the defect
		
		//eg. resource : rest/api/2/issue/MYP-2/comment/10001
		
		System.out.println();
		given().header("Cookie",name+"="+value).
		when().delete("rest/api/2/issue/"+key+"/comment/"+commentId).
		then().assertThat().statusCode(204).and().contentType(ContentType.JSON);
		
				
				
		
	}

}
