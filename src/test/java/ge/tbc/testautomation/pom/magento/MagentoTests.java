package ge.tbc.testautomation.pom.magento;

import ge.tbc.testautomation.util.Retry;
import ge.tbc.testautomation.util.RetryAnalyzer;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.TEE;

@Test(description = "Allure Pom Tests")
@Listeners({AllureTestNg.class})
public class MagentoTests extends BaseTest {
    @Epic("Magento E2E Tests")
    @Feature("Functional UI Tests")
    @Test(priority = 1, enabled = true)
    public void colorChangeTest() {
        homeSteps
                .waitForOffersToLoad()
                .locateAllOffersWhichColorsCanBeChanged()
                .verifyOffersExist()
                .chooseRandomThreeOffers()
                .verifyColorChangesReflectOnImage();

    }

    @Test(priority = 2)
    @Story("Color changes")
    @Description("Verifies that product image reflects selected color")
    @Severity(SeverityLevel.NORMAL)
    public void addToCartTest() {
        homeSteps
                .searchForItem(TEE);
        searchResultsSteps
                .locateFirstSearchResult()
                .goToItemsPage();
        itemsSteps
                .waitUntilStockStatusLabelBecomesVisible()
                .getProductsTitle()
                .chooseSize()
                .chooseColor()
                .goToCheckout()
                .validateCartSuccessMessageIsVisible()
                .clickOnMyCartBtn()
                .getProductItemName()
                .getProductItemPrice()
                .validateItemNamesAreSame();
    }

    @Test(dependsOnMethods = {"addToCartTest"}, priority = 3)
    @Story("Add to cart")
    @Description("Adds an item to the cart and verifies success message")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteFromCart() {
        itemsSteps
                .clickOnMyCartBtn()
                .clickRemoveItemBtn()
                .clickConfirmRemovalBtn()
                .clickOnMyCartBtn()
                .validateCartIsEmpty();
    }

    @Test(priority = 4)
    @Description("Test to validate the behavior when an out-of-stock offer is selected.")
    @Severity(SeverityLevel.NORMAL)
    public void outOfStockOfferTest() {
        homeSteps
                .clickHotSellersFirstItem();
        itemsSteps
                .chooseSize()
                .chooseColor()
                .goToCheckout()
                .validateQuantityErrorMessage();
    }

    @Test(priority = 5)
    public void reviewNumberTest() {
        homeSteps
                .searchForItem(TEE);
        searchResultsSteps
                .locateFirstSearchResult()
                .goToItemsPage();
        itemsSteps
                .waitUntilStockStatusLabelBecomesVisible()
                .getNumberOfReviews()
                .goToReviewsPage();
        reviewsSteps
                .waitForReviewsToAppear()
                .countHowManyReviewsAreThere();
    }

    @Test(priority = 6)
    public void mobileNavigationTest() {
        homeSteps
                .validateTopNavBarIsVisible()
                .validateSignNavLinkIsVisible()
                .validateCreateAnAccountLinkIsVisible()
                .changeViewPortSize()
                .clickNavToggleBtn()
                .clickOnMenu()
                .validateNavBarLinksAreVisible()
                .clickOnAccountLink()
                .validateSignNavLinkIsVisible()
                .validateCreateAnAccountLinkIsVisible();
    }

    @Test(priority = 7)
    public void saveToFavoritesWhileUnauthorizedTest() {
        homeSteps
                .setViewPortSizeForDesktop()
                .searchForItem(TEE);
        searchResultsSteps
                .locateFirstSearchResult()
                .goToItemsPage();
        itemsSteps
                .waitUntilStockStatusLabelBecomesVisible()
                .getProductsTitle()
                .chooseSize()
                .chooseColor()
                .clickAddToWishlistButton()
                .validateWishlistErrorMsgIsVisible()
                .clickCreateAnAccountBtn();
        signUpSteps
                .enterFirstName()
                .enterLastName()
                .enterEmail()
                .enterPassword()
                .confirmPassword()
                .clickSubmitBtn()
                .saveCredentialsTo(creds);
        wishlistSteps
                .waitForWishlistAlert()
                .validateAlertSaysItemSuccessfullyAdded()
                .validateWelcomeMessageIsCorrectlyDisplayed();
    }

    @Test(priority = 8,  dependsOnMethods = "saveToFavoritesWhileUnauthorizedTest", retryAnalyzer = RetryAnalyzer.class)
    @Retry(maxRetries = 2)
    public void purchaseItem() {
        wishlistSteps
                .clickDropDown()
                .signOut();
        homeSteps
                .clickSignInLink();
        signInSteps
                .validateWeAreOnSignInPage()
                .enterEmail(creds.email)
                .enterPassword(creds.password)
                .clickLogin();
        myAccountSteps
                .clickOnMyWishListLink();
        itemsSteps
                .getProductItemName()
                .validateItemNamesAreSame()
                .scrollToAddToCartBtn()
                .goToCheckout()
                .clickOnMyCartBtn()
                .validateWeAreInShoppingCart()
                .continueShopping();
        shippingSteps
                .clickOnProceedToCheckoutBtn()
                .enterStreetAddress()
                .enterCityName()
                .enterPostalCode()
                .selectState()
                .enterPhoneNumber()
                .checkShippingMethod()
                .clickNextBtn();
        paymentSteps
                .retrieveCartSubtotal()
                .retrieveShippingPrice()
                .retrieveTotalPrice()
                .validateShippingIsAddedCorrectly()
                .validateShippingContentIsDisplayedCorrectly()
                .clickToApplyDiscountCode()
                .enterDiscountCode()
                .clickApplyDiscountBtn()
                .waitErrorMessageToAppear()
                .validateErrorMessage()
                .clickPlaceOrder();
        successSteps
                .validateOrderNumberIsDisplayed()
                .validatePageTitleIsSuccess();
    }
}
