package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.magento.WishlistPage;
import org.testng.asserts.SoftAssert;

import static ge.tbc.testautomation.data.Constants.ADDED_TO_YOUR_WISHLIST;
import static ge.tbc.testautomation.data.Constants.EXPECTED_MESSAGE_ABOUT_CONFIRMATION;

public class WishlistSteps {
    WishlistPage wishlistPage;
    Page page;
    private String wishlistSuccessAlertMsg;
    SignUpSteps signUpSteps;
    SoftAssert softAssert = new SoftAssert();

    public WishlistSteps(Page page)
    {
        this.page = page;
        this.wishlistPage = new WishlistPage(page);
        this.signUpSteps = new SignUpSteps(page);
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

    public WishlistSteps validateWelcomeMessageIsCorrectlyDisplayed()
    {
        wishlistPage.welcomeBanner.waitFor();
        String welcomeText = wishlistPage.welcomeBanner.textContent().replaceAll("\\s+", " ").trim();
        softAssert.assertTrue(welcomeText.contains("Welcome, " + signUpSteps.testFirstName + " " + signUpSteps.testLastName + "!"));
        return this;
    }
}
