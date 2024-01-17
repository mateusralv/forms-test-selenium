import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class googleTest {
	
	@Test
	public void openGoogleSite() {
		//WebDriver driver= new FirefoxDriver(); // Rodar no Firefox
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");//Abre o Site
		System.out.println(driver.getTitle());//Imprime no console o titulo
		Assert.assertEquals("Google", driver.getTitle());
		
		// Definir posiçao
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		
		//FEchar browser e encerra os processos
		driver.quit();
	}
}
