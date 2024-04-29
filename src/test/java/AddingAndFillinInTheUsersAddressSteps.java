import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.datatable.DataTable;



import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AddingAndFillinInTheUsersAddressSteps {
    private static WebDriver driver;
    //logowanie i asercja sprawdzajaca czy strona jest poprawna
    @BeforeAll
    public static void setUp(){
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Given ("User is on the login page")
    public void loginPage(){
        driver.get(" https://mystore-testlab.coderslab.pl");


    }
    @And ("clicks the icon Sign in")
    public void iconSignIn(){
        WebElement logIn = driver.findElement(By.id("_desktop_user_info"));
        logIn.click();


    }
    @When("User logs in with")
    public void userLogsInWith(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {

            driver.findElement(By.id("field-email")).sendKeys(row.get("email"));
            driver.findElement(By.id("field-password")).sendKeys(row.get("password"));
        }

    }
    @And ("Clicks to Sign in")
    public void clicksToSignIn(){
        driver.findElement(By.id("submit-login")).click();
    }

    @Then ("Clicks to addresses and navigate to the addresses page")
    public void clicksToAddressesAndNavigate() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement addAddresses = driver.findElement(By.id("addresses-link"));
        addAddresses.click();

        String expectedUrl = "https://mystore-testlab.coderslab.pl/index.php?controller=addresses";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
        System.out.println(actualUrl);

    }



        // wprowadzanie i sprawdzanie adresu
        @And ("Clicks to the create new address button")
        public void clicksToTheCreateNewAddressButton(){
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a")).click();

        }
        @When("Fill out the form with data")
        public void fillOutTheFormWithData(DataTable dataTable){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> row : data) {

                driver.findElement(By.id("field-alias")).sendKeys(row.get("alias"));
                driver.findElement(By.id("field-address1")).sendKeys(row.get("address"));
                driver.findElement(By.id("field-city")).sendKeys(row.get("city"));
                driver.findElement(By.id("field-postcode")).sendKeys(row.get("zip"));
                driver.findElement(By.id("field-id_country")).sendKeys(row.get("country"));
                driver.findElement(By.id("field-phone")).sendKeys(row.get("phone"));

            }
        }
    @Then ("Check the data in the added address is correct-check alias,address,city,zip,country,phone")
    public void checkTheAddedAddressIsCorrect() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        WebElement newAlias = driver.findElement(By.id("field-alias"));
        String actualAlias = newAlias.getText().trim();
        String expectedAlias ="dom";
        Assertions.assertEquals("dom" + actualAlias, expectedAlias, actualAlias);

        WebElement newAddress = driver.findElement(By.id("field-address1"));
        String actualAddress=newAddress.getText();
        String expectedAddress="ul.Cicha";
        Assertions.assertEquals( "ul.Cicha" + actualAddress,expectedAddress,actualAddress);


        WebElement newCity = driver.findElement(By.id("field-city"));
        String actualCity=newCity.getText();
        String expectedCity="Gdansk";
        Assertions.assertEquals("Gdansk" + actualCity,expectedCity,actualCity);

        WebElement newZip = driver.findElement(By.id("field-postcode"));
        String actualZip= newZip.getText();
        String expectedZip="80-102";
        Assertions.assertEquals( "80-102" + actualZip,expectedZip,actualZip);



        try {

            WebElement countrySelect = driver.findElement(By.id("field-id_country"));
            Select select = new Select(countrySelect);

            //List<WebElement> options = select.getOptions();
            // Assertions.assertEquals(1, options.size(), "Oczekiwano tylko jednej opcji, ale znaleziono: " + options.size());
            String actualCountry = select.getFirstSelectedOption().getText();
            String expectedCountry = "Polska";
            Assertions.assertEquals(expectedCountry, actualCountry, "Oczekiwano kraju Polska, ale znaleziono: " + actualCountry);
            System.out.println(actualCountry);
        } catch (NoSuchElementException e) {
            System.out.println("Nie można znaleźć elementu o podanym id: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Wystąpił inny błąd podczas wykonywania testu: " + e.getMessage());
        }



        WebElement newPhone = driver.findElement(By.id("field-phone"));
        String actualPhone= newPhone.getText();
        String expectedPhone="710-100-100";
        Assertions.assertEquals( "710-100-100" + actualPhone,expectedPhone,actualPhone);

    }
    @Then ("Clicks save the new address")
    public void clicksSaveTheNewAddress() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button")).click();
    }

}



