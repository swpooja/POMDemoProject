package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductListPgae;

public class PlaceOrderTest {
	WebDriver driver;
	LoginPage loginPage;
	ProductListPgae listPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	public PlaceOrderTest() {
		TestBase.initDriver();
		driver = TestBase.getDriver();
		loginPage = new LoginPage(driver);
		listPage = new ProductListPgae(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}
	
	@BeforeTest
	public void setup() {
		TestBase.openUrl("https://www.saucedemo.com/");
		loginPage.loginIntoApp("standard_user", "secret_sauce");
		
	}
  @Test(priority=2)
  public void addItem() {
	  listPage.addToCart();
	  listPage.viewCart();
	  Assert.assertTrue(cartPage.isItemAdded());
  }
  @Test(priority=1)
  public void validLogin() {
	  Assert.assertTrue(listPage.isOnProduct());
  }
  @Test(priority=3)
  public void checkoutTest() {
	 cartPage.checkoutItems();
  }
  //@Test(priority=3)
  public void removeItemTest() {
	  cartPage.removeItem();
  }
  //@Test(priority=4)
  public void continueShoppingTest() {
	  cartPage.continueShopping();
  }
  @Test(priority=4)
  public void checkoutInfo() {
	  checkoutPage.checkoutLogin("TestUser", "one", "324562");
	  checkoutPage.checkoutOrder();
	  Assert.assertTrue(checkoutPage.isOrderSuccess());
  }
}
