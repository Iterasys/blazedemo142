// 1 - bibliotecas / imports
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; // biblioteca principal do Selenium
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // biblioteca do ChromeDriver

import io.github.bonigarcia.wdm.WebDriverManager;


// 2 - classe
public class PassagemTest {
    // 2.1 - Atributos
    private WebDriver driver; // objeto do Selenium

    // 2.2 - Funções e Métodos

    // Antes do Teste
    @BeforeEach
    public void iniciar() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // instanciar o objeto do Selenium como ChromeDriver
        driver.manage().window().maximize(); // maximiza a janela do browser
    }

    // Depois do Teste
    @AfterEach
    public void finalizar() {
        driver.quit(); // destroi o objeto do Selenium
    }

    // Teste
    @Test
    public void comprarPassagem() {
        driver.get("https://www.blazedemo.com"); // abre o site Blazedemo
        // seleciona origem, destino e aperta o botão "Find Flights"
       
        // combo Origem
        driver.findElement(By.name("fromPort")).click(); // clica no combo
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'São Paolo']")).click();
        }

        // combo Destino
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.click(); // clica no combo
            dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();

        }

        // Clicar no botão
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Transição de Página

        // Verifica se foi realizado o login e a pesquisa dos vôos
        assertEquals("Flights from São Paolo to Cairo:", 
            driver.findElement(By.cssSelector("h3")).getText());

        // clica no botão do vôo desejado
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();

        // verifica se está na pagina de compra
        assertEquals("Your flight from TLV to SFO has been reserved.",
            driver.findElement(By.cssSelector("h2")).getText());


    } // final do comprarPassagem

} // final da classe PassagemTest
