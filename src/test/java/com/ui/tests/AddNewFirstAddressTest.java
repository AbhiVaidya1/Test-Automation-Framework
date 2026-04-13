package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;

public class AddNewFirstAddressTest extends TestBase {

	private MyAccountPage myAccountPage;
	//private AddressPage addressPage;
	private AddressPOJO address;

	@BeforeMethod(description = "Valid First time user logs into the application")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("dejic37640@helesco.com", "Password");

		// address = new AddressPOJO("Google", "address line1", "address line2", "city",
		// "12345", "7045663552", "7045663552", "other", "office address",
		// "California");
		address = FakeAddressUtility.getFakeAddress();

	}

	@Test
	public void AddNewAddress() {
		// myAccountPage.goToAddAddressPage().saveAddress();
		String newAddress = myAccountPage.goToAddAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
	}

}
