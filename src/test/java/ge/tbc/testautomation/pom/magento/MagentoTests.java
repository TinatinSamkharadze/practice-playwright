package ge.tbc.testautomation.pom.magento;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.TEE;

public class MagentoTests extends BaseTest {

    @Test(priority = 1)
    public void colorChangeTest() {
        homeSteps
                .waitForOffersToLoad()
                .locateAllOffersWhichColorsCanBeChanged()
                .verifyOffersExist()
                .chooseRandomThreeOffers()
                .verifyColorChangesReflectOnImage();

    }

    @Test(priority = 2)
    public void addToCartTest() {
        homeSteps
                .searchForItem(TEE);
        searchResultsSteps
                .locateFirstSearchResult()
                .goToItemsPage();
        itemsSteps
                .waitUntilStockStatusLabelBecomesVisible()
                .getProductsTitle()
                .getProductPriceValue()
                .chooseSize()
                .chooseColor()
                .goToCheckout()
                .validateCartSuccessMessageIsVisible()
                .clickOnMyCartBtn()
                .getProductItemName()
                .getProductItemPrice()
                .validateItemNamesAreSame()
                .validateItemPricesAreSame();
    }

    @Test(dependsOnMethods = {"addToCartTest"}, priority = 3)
    public void deleteFromCart() {
        itemsSteps
                .clickOnMyCartBtn()
                .clickRemoveItemBtn()
                .clickConfirmRemovalBtn()
                .clickOnMyCartBtn()
                .validateCartIsEmpty();
    }

    @Test(priority = 4)
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
                .countHowManyReviewsAreThere()
                .validateNumberOfReviewsAreTheSame();
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

    @Test(priority = 8,  dependsOnMethods = "saveToFavoritesWhileUnauthorizedTest")
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
