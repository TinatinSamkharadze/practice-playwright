package ge.tbc.testautomation.pom;

import ge.tbc.testautomation.BaseTest;
import ge.tbc.testautomation.steps.magento.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class MagentoTestsWithPOM extends BaseTest {
    HomeSteps homeSteps;
    SearchResultsSteps searchResultsSteps;
   ItemsSteps itemsSteps;
   ReviewsSteps reviewsSteps;
   SignUpSteps signUpSteps;
   WishlistSteps wishlistSteps;
    @BeforeMethod
    public void resetContext()
    {
        homeSteps = new HomeSteps(page);
        searchResultsSteps = new SearchResultsSteps(page);
        itemsSteps = new ItemsSteps(page);
        reviewsSteps = new ReviewsSteps(page);
        signUpSteps = new SignUpSteps(page);
        wishlistSteps = new WishlistSteps(page);
        page.setViewportSize(WIDTH_FOR_DESKTOP, HEIGHT_FOR_DESKTOP);

    }

    @Test
    public void colorChangeTest()
    {
        page.navigate(MAGENTO_URL);
        homeSteps
                .waitForOffersToLoad()
                .locateAllOffersWhichColorsCanBeChanged()
                .verifyOffersExist()
                .chooseRandomThreeOffers()
                .verifyColorChangesReflectOnImage();

    }

    @Test
    public void addToCartTest()
    {
        page.navigate(MAGENTO_URL);
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
                .clickAddToCartBtn()
                .validateCartSuccessMessageIsVisible()
                .clickOnMyCartBtn()
                .getProductItemName()
                .getProductItemPrice()
                .validateItemNamesAreSame()
                .validateItemPricesAreSame();
    }

    @Test(dependsOnMethods = {"addToCartTest"})
    public void deleteFromCart()
    {
        itemsSteps
                .clickRemoveItemBtn()
                .clickConfirmRemovalBtn()
                .clickOnMyCartBtn()
                .validateCartIsEmpty();
    }

    @Test
    public void outOfStockOfferTest()
    {
        page.navigate(MAGENTO_URL);
        homeSteps
                .clickHotSellersFirstItem();
        itemsSteps
                .chooseSize()
                .chooseColor()
                .clickAddToCartBtn()
                .validateQuantityErrorMessage();
    }

    @Test
    public void reviewNumberTest()
    {
        page.navigate(MAGENTO_URL);
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

    @Test
    public void mobileNavigationTest()
    {
        page.navigate(MAGENTO_URL);
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

    @Test
    public void saveToFavoritesWhileUnauthorizedTest()
    {
        page.navigate(MAGENTO_URL);
        homeSteps
                .searchForItem(TEE);
        searchResultsSteps
                .locateFirstSearchResult()
                .goToItemsPage();
        itemsSteps
                .waitUntilStockStatusLabelBecomesVisible()
                .getProductsTitle()
                .clickAddToWishlistButton()
                .validateWishlistErrorMsgIsVisible()
                .clickCreateAnAccountBtn();
        signUpSteps
                .enterFirstName()
                .enterLastName()
                .enterEmail()
                .enterPassword()
                .confirmPassword()
                .clickSubmitBtn();
       wishlistSteps
               .waitForWishlistAlert()
               .validateAlertSaysItemSuccessfullyAdded()
               .validateWelcomeMessageIsCorrectlyDisplayed();
    }
}
