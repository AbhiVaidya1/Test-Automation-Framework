package com.ui.tests;

import static com.constants.Size.L;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.SearchResultPage;

public class ProductCheckoutTest extends TestBase {

	private static final String SEARCH_TERM = "Printed Summer Dress";
	private SearchResultPage searchResultPage;

	@BeforeMethod(description = "User logs into the application and searches for a product")
	public void setup() {
		searchResultPage = homePage.goToLoginPage().doLoginWith("dejic37640@helesco.com", "Password")
				.searchForProduct(SEARCH_TERM);
	}

	@Test(description = "Verify if the logged in User is able to buy dress", groups = { "e2e", "smoke", "sanity" })
	public void checkoutTest() {
		// searchResultPage.clickOnTheProductAt(0).changeSize(Size.L);
		searchResultPage.clickOnTheProductAtIndex(1).changeSize(L).addProductTocart().proceedToCheckout()
				.goToConfirmAddressPage()
				.goToShippmentPage()
				.goToPaymentPage();

	}

}
