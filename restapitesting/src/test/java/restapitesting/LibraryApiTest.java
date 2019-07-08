package restapitesting;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.Payload;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LibraryApiTest {
	
	Properties prop;
	@BeforeTest
	public void initialization() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\file\\env.properties");
		prop.load(fis);
		
		
	}
	
	@Test(dataProvider="data")
	public void addBookApiTest(String isbn,String aisle)
	{
	
	baseURI =  prop.getProperty("baseURL");
	
	Response resp= given().header("Content-Type","application/json")
			.body(Payload.addBook(isbn, aisle))
	.when().post("/Library/Addbook.php")
	.then().assertThat().statusCode(200).
	and().contentType(ContentType.JSON).and().body("Msg", equalTo("successfully added"))
	.extract().response();
	
	JsonPath path = new JsonPath(resp.asString());
	System.out.println(path.get("ID"));
	
	
	}
	
	@DataProvider(name="data")
	public Object[][] dataSetup()
	{
		Object [][] data = {{"helloworld","test3088"},
							{"unique","738808"},
							{"hatsoff","goo2092"}
				     		};
		return data;
	}
}
