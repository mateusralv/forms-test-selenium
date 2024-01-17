import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAlert {
	@Test
	public void alertaSimples() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		//declaration and instantiation
		String currentUserDir = System.getProperty("user.dir");
		String baseURL = "file:///" + currentUserDir + "/src/main/resources/componentes.html";
		driver.get(baseURL);
		
		//Get the nome element and fill the Nome Field
		WebElement alerta = driver.findElement(By.id("alert"));
		alerta.click();														//Identifica o elemento que quer escrever
		Alert alert = driver.switchTo().alert();
		String valueAlert = alert.getText();
		
		Assert.assertEquals("Alert Simples", valueAlert);
		
		alert.accept();
	
		//Fechar browser e encerrar os processos
		driver.quit();
	}
	
	@Test
	public void alertaConfirm() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		//declaration and instantiation
		String currentUserDir = System.getProperty("user.dir");
		String baseURL = "file:///" + currentUserDir + "/src/main/resources/componentes.html";
		driver.get(baseURL);
		
		//Get the nome element and fill the Nome Field
		WebElement alerta = driver.findElement(By.id("confirm"));
		alerta.click();
		Alert alert = driver.switchTo().alert();
		String valueAlert = alert.getText();
		
		Assert.assertEquals("Confirm Simples", valueAlert);
		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
		
		driver.quit();
	}	
}
