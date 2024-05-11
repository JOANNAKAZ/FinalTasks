import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

    protected static WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProduct(String product) {
       WebElement selectProduct= driver.findElement(By.xpath("//*[@id=\"search_widget\"]/form/input[2]"));
       selectProduct.sendKeys(product);
       selectProduct.submit();

    }
    public void choiceOfProduct(){
        WebElement choiceOfProduct= driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div[1]/article/div/div[1]/a"));
        choiceOfProduct.click();

        String discountText=driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[2]/div/span[2]")).getText();
        Assertions.assertEquals("SAVE 20%", discountText);
        System.out.println("Rabat wynosi 20%");

    }

    public void selectSize(String size) {
        driver.findElement(By.id("group_1")).click();
        driver.findElement(By.cssSelector("[title='" + size + "']")).click();
    }
    public void selectQuantity(int quantity) {
        WebElement quantityElement = driver.findElement(By.id("quantity_wanted"));
        quantityElement.click();
        quantityElement.sendKeys(Keys.BACK_SPACE);
        quantityElement.sendKeys(Keys.BACK_SPACE);
        quantityElement.sendKeys(String.valueOf(quantity));
    }
    public void addToCart() {
        driver.findElement(By.cssSelector("div.add")).click();
    }
}



