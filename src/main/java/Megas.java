import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Megas {

	private WebDriver driver;

	public Megas(WebDriver driver) {
		this.driver = driver;
	}
	
	public void fillInField(String key_idField, String key_text) {
		driver.findElement(By.id(key_idField)).sendKeys(key_text);
	}

	public void getValueField(String key_idField, String key_text) {
		driver.findElement(By.id((key_idField)).getAttribute("value"));
	}
	
	
}
