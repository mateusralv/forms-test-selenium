import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;

public class FormsTest {

	private WebDriver driver;
	private Megas megas;

	public FormsTest(WebDriver driver) {
		this.driver = driver;
	}

	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		//declaration and instantiation
		String currentUserDir = System.getProperty("user.dir");
		String baseURL = "file:///" + currentUserDir + "/src/main/resources/componentes.html";
		driver.get(baseURL);//Abre o Site		
		megas = new Megas(driver);
	}
	
	@After
	public void tearDown() {
		driver.quit();		
	}
	
	@Test
	public void fillNameField() {
		//Get the nome element and fill the Nome Field
		megas.fillInField("elementosForm:nome", "User 1");														//Identifica o elemento que quer escrever
		Assert.assertEquals("User 1", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	
	@Test
	public void fillSugestoesField() {
		//Get the Area element and fill the Area Field
		WebElement area = driver.findElement(By.id("elementosForm:sugestoes"));  				//Identifica o elemento que quer escrever
		area.sendKeys("Fill the field"); 														//Escreve no elemento
		String valuearea = area.getAttribute("value");
		Assert.assertEquals("Fill the field", valuearea);
	}
	
	@Test
	public void fillSexoField() {
		//Interação com Radio Button
		WebElement radio = driver.findElement(By.id("elementosForm:sexo:1"));  					//Identifica o elemento que quer escrever
		radio.click();
		//Boolean radio = area.getAttribute("value");
		Assert.assertTrue(radio.isSelected());	
	}
	
	@Test
	public void fillComidaField() {
		//Interaçao com Checkbox
		WebElement checkPizza = driver.findElement(By.id("elementosForm:comidaFavorita:2")); 
		checkPizza.click();
		Assert.assertTrue(checkPizza.isSelected());
	}
	
	@Test
	public void fillEscolaridadeField() {
		//Interagir com Select
		WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(escolaridade);
		combo.selectByVisibleText("Superior");
		String selected = combo.getFirstSelectedOption().getText();
		Assert.assertEquals("Superior", selected);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean status = false;
		for (WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
	}
	
	@Test
	public void fillEsportesField() {
		//Multipla escolha
		WebElement esportes = driver.findElement(By.id("elementosForm:esportes"));
		Select resposta = new Select(esportes);
		resposta.selectByVisibleText("Natacao");
		resposta.selectByVisibleText("Corrida");
		resposta.selectByVisibleText("Karate");
		List<WebElement> respostas = resposta.getAllSelectedOptions();
		Assert.assertEquals(3, respostas.size());
	}
	
	@Test
	public void clickButtonSimples() {
		WebElement buttonSimple = driver.findElement(By.id("buttonSimple")); 
		buttonSimple.click();
		String valueButtonSimple = buttonSimple.getAttribute("value");
		Assert.assertEquals("Obrigado!", valueButtonSimple);
		
	}
	@Test
	public void clickButtonVoltar() {
		//Links
		WebElement buttonVoltar = driver.findElement(By.linkText("Voltar"));
		buttonVoltar.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));		
		WebElement resultado = driver.findElement(By.id("resultado"));
		
		String resultados = resultado.getText();
		Assert.assertEquals("Voltou!", resultados);
	}
	
	@Test
	@Ignore
	public void fillAllForm() {
		//fill in the required fields
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome"); 										
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		
		
		//Send the Form
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		
		//Confirmar o Formulario
		
		//Confirm the completed information
		Assert.assertEquals("Cadastrado!", driver.findElement(By.xpath("//span[.=\"Cadastrado!\"]")).getText());
		Assert.assertEquals("Nome: User 1", driver.findElement(By.xpath("//div[@id=\"descNome\"]")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.xpath("//div[@id=\"descSexo\"]")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.xpath("//div[@id=\"descComida\"]")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.xpath("//div[@id=\"descEscolaridade\"]")).getText());
		Assert.assertEquals("Esportes: Natacao Corrida Karate", driver.findElement(By.xpath("//div[@id=\"descEsportes\"]")).getText());
		Assert.assertEquals("Sugestoes: Fill the field", driver.findElement(By.xpath("//div[@id=\"descSugestoes\"]")).getText());
	}
}
