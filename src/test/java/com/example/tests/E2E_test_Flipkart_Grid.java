package com.example.tests;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.example.pageObjects.CheckOutPage_Flipkart;
import com.example.pageObjects.LoginPage_Flipkart;
import com.example.pageObjects.ProductPage_Flipkart;
import com.example.resources.Base;

public class E2E_test_Flipkart_Grid extends Base {

	WebDriver driver;
	
	HashMap<String, List<String>> loginData;
	LoginPage_Flipkart ln=new LoginPage_Flipkart(driver);
	ProductPage_Flipkart prd=new ProductPage_Flipkart(driver);
	CheckOutPage_Flipkart chk=new CheckOutPage_Flipkart(driver);
	@Test
	public void flipkartLogin() throws InterruptedException, Exception
	{	
		
		loginData=Base.readFileOnce("LoginDetails");
		
		System.out.println("Trying with :"+loginData.get("UserName"));
		System.out.println(driver.getTitle());
		
		for(int i=0;i<=loginData.size();i++) {
				Base.wait2Seconds();
				ln.userName().click();
				ln.userName().sendKeys(loginData.get("UserName").get(i));
				ln.password().click();
				ln.password().sendKeys(loginData.get("Password").get(i));
				ln.submit().click();
		
		System.out.println("Login success");
		Base.wait2Seconds();
		
		prd.productSearch().click();
		prd.productSearch().sendKeys(loginData.get("productName").get(i));
		prd.productSearch().sendKeys(Keys.ENTER);
		
		System.out.println("searchable product is :"+loginData.get("productName").get(i));
		Base.wait2Seconds();
		
		prd.productToSelect().get(4).click();
		String selectedProduct=prd.productToSelect().get(4).getText();
		
		Base.wait2Seconds();
		for (String handle : driver.getWindowHandles()) {
			 
		    driver.switchTo().window(handle);
		    }
		
		if(prd.productText().getText().contains(selectedProduct)) {
				Base.scrollToElementAndClick(prd.addToCart());
				Base.scrollToElementAndClick(prd.placeOrder());
		}else {
			System.out.println("product mismatch");
		}
		
		chk.customerName().sendKeys(loginData.get("CustomerName").get(i));
		chk.customerPhoneNumber().sendKeys(loginData.get("PhoneNumber").get(i));
		chk.pincode().sendKeys(loginData.get("Pincode").get(i));
		chk.locality().sendKeys(loginData.get("Locality").get(i));
		chk.addressArea().sendKeys(loginData.get("AddressArea").get(i));
		chk.cityOrtownOrDistrict().sendKeys(loginData.get("cityOrTownOrDist").get(i));
		chk.stateSelect().click();
		Base.getValues(chk.stateTobeSelect(), "Karnataka");
		

	}
		chk.clickOnUser().click();
		

	}
}
