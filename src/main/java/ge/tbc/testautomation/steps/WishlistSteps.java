package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.WishlistPage;
import org.testng.asserts.SoftAssert;

import static ge.tbc.testautomation.data.Constants.ADDED_TO_YOUR_WISHLIST;
import static ge.tbc.testautomation.data.Constants.EXPECTED_MESSAGE_ABOUT_CONFIRMATION;

public class WishlistSteps {
    WishlistPage wishlistPage;
    Page page;
    private String wishlistSuccessAlertMsg;
    SoftAssert softAssert = new SoftAssert();

    public WishlistSteps(Page page)
    {
        this.page = page;
        this.wishlistPage = new WishlistPage(page);
    }

    public String getWishlistSuccessAlertMsg()
    {
        return this.wishlistSuccessAlertMsg;
    }

    public WishlistSteps waitForWishlistAlert()
    {
        Locator wishListSuccessAlert = wishlistPage.wishlistSuccessAlert;
        wishListSuccessAlert.waitFor();
        this.wishlistSuccessAlertMsg = wishListSuccessAlert.textContent().replaceAll("\\s+", " ").trim();
        return this;
    }

    public WishlistSteps validateAlertSaysItemSuccessfullyAdded()
    {
        softAssert.assertTrue(
                getWishlistSuccessAlertMsg().contains(ADDED_TO_YOUR_WISHLIST),
                EXPECTED_MESSAGE_ABOUT_CONFIRMATION
        );
        return this;
    }
}
