package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseUtil.testclass;
import library.utility;

public class PlaceOrderNonLoggedIn extends testclass{
	
	@Test
	public void test01HomePage() throws InterruptedException, IOException {
		driver.get("https://www.zamaorganics.com/");
		new WebDriverWait(driver, 60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ticker']")));
		new WebDriverWait(driver, 60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//small[@class='delivery_at'])[2]")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Vegetables']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Fruits']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Grains and Cereals']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src='https://www.zamaorganics.com/media/logo/stores/2/zama_logo_1.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src='https://www.zamaorganics.com/media/Bottom_Right_Banners-01.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/customer/account/login']")).isDisplayed());
	}
	
	@Test
	public void test02SelectItemPage() throws InterruptedException {
		JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,700)");
		new WebDriverWait(driver, 30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='product-image-photo'])[1]")));
        driver.findElement(By.xpath("(//a[@class='action tocart primary new_add_to_cart magnetoAddcart-2680'])[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("(//img[@class='product-image-photo'])[1]")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='member_block']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='price'])[1]")).isDisplayed());
	}
	
	@Test
	public void test03PincodeEntryPage() throws InterruptedException {
		new WebDriverWait(driver, 30L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='delivery_postcode']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='delivery_postcode']")).sendKeys("400050");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Select']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='delivery_postcode']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("(//img[@class='signin-popup-logo'])[4]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='delivery_date']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("(//span[text()='Added'])[1]")).isDisplayed());
	}
	
	@Test
	public void test04CartPage() {
		driver.findElement(By.xpath("//span[@class='cart-icon']")).click();
		new WebDriverWait(driver, 20L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Go to Checkout']")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Go to Checkout']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='subtotal']")).isDisplayed());
	}
	
	@Test
	public void test05CheckoutPage() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Go to Checkout']")).click();
		new WebDriverWait(driver, 20L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Shipping Address']")));
		driver.findElement(By.xpath("(//input[@id='customer-email'])[1]")).sendKeys("kshitijsalunkhe@gamil.com");
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//input[@name='firstname'])[3]")).sendKeys("kshitij");
		driver.findElement(By.xpath("(//input[@name='lastname'])[3]")).sendKeys("salunkhe");
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("8888883803");
		driver.findElement(By.xpath("//input[@name='custom_attributes[flat_no]']")).sendKeys("A2 Galaxy Apartments");
		driver.findElement(By.xpath("//input[@name='street[0]']")).sendKeys("BJ Road");
		driver.findElement(By.xpath("//input[@name='custom_attributes[landmark_location]']")).sendKeys("Galaxy Apartments");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Bandra");
		driver.findElement(By.xpath("(//input[@name='postcode'])[1]")).sendKeys("400050");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Place Order']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("(//span[text()='Apply Discount'])[2]")).isDisplayed());
	}
	
	@Test
	public void test06PamentGatewayPage() throws InterruptedException {
		new WebDriverWait(driver, 60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Place Order')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
		Thread.sleep(5000);
		WebElement iframe = driver.findElement(By.xpath("//iframe[@class='razorpay-checkout-frame']"));
		driver.switchTo().frame(iframe);
		new WebDriverWait(driver, 60L).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Online Payment']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Online Payment']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src='https://cdn.razorpay.com/static/assets/trustedbadge/rtb-live.svg']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='instrument slotted-option svelte-1u727jy']")).isDisplayed());
	}
	
}