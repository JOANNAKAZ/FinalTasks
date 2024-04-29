import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class TestShop {
    protected static WebDriver driver;


    @BeforeAll
    public static void SetUp(){
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(" https://mystore-testlab.coderslab.pl");


    }

    @Test
    public  void shopping() {


        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("janusz.szot@wp.pl", "Janusz");

            ProductPage productPage = new ProductPage(driver);
            productPage.selectProduct("Hummingbird Printed Sweater");
            productPage.choiceOfProduct();
            productPage.selectSize("M");
            productPage.selectQuantity(5);
            productPage.addToCart();

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.proceedToCheckout();
            checkoutPage.confirmAddress();
            checkoutPage.selectDeliveryOption();
            checkoutPage.selectPaymentOption();
            checkoutPage.agreeToTerms();
            checkoutPage.placeOrder();



            //Thread.sleep(500);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));



            // robie screenshot z potwierdzeniem zamówienia i kwotą
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Określam ścieżkę do zapisania zrzutu ekranu
            Path destPath = Paths.get("C","Users","asiak","Dysk Google" ,"potwierdzenie-zamówienia.png");
            // Utwórz katalogi,
            Files.createDirectories(destPath.getParent());
            // Zapisz zrzut ekranu
            Files.copy(scrFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);



        } catch (NoSuchElementException |TimeoutException |IOException e) {
            //e.printStackTrace();
            //drukowanie wyjatku
        } finally {
            driver.quit();
        }
    }
}


