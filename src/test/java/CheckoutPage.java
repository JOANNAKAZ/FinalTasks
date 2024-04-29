import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    protected static WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")).click();
    }
    public void confirmAddress() {
        driver.findElement(By.xpath("//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")).click();
    }

    public void selectDeliveryOption() {
       WebElement deliveryOption= driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button"));
       if(!deliveryOption.isSelected()){
           deliveryOption.click();
       }
    }

    public void selectPaymentOption() {
        driver.findElement(By.id("payment-option-1")).click();
    }

    public void agreeToTerms() {
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
    }
    public void placeOrder() {
        driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();
    }
}


