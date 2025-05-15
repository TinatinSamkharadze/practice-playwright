package ge.tbc.testautomation.pom.toolwebsite.sharedcontext;

import org.testng.annotations.Test;

public class SharedContextTests extends SharedBaseTest{


    @Test(priority = 1)
    public void favouritesTest() {
        accountSteps
                .goToSignInPage();
        signInSteps
                .validateWeAreOnCorrectPage()
                .validateWeHaveCorrectCredentials(testUser.getEmail(), testUser.getPassword())
                .enterEmail()
                .enterPassword()
                .clickLoginButton();
        accountSteps
                .validateWeAreOnRightPage()
                .goToHomePage();
        homeSteps
                .clickOnFirstProduct();
        productSteps
                .clickAddToFavouritesBtn()
                .validateSuccessMessage()
                .clickToggleNavLink()
                .signOut();
        accountSteps
                .goToSignInPage();
        signInSteps
                .validateWeAreOnCorrectPage()
                .validateWeHaveCorrectCredentials(testUser.getEmail(), testUser.getPassword())
                .enterEmail()
                .enterPassword()
                .clickLoginButton();
        accountSteps
                .goToHomePage();
        homeSteps
                .clickOnFirstProduct();
        productSteps
                .clickAddToFavouritesBtn()
                .validateProductIsAlreadyInFavourites();
    }

    @Test(priority = 2)
    public void filterTest()
    {
        homeSteps
                .checkScrewDriver()
                .countHowManyScrewDriverProductsAreThere()
                .checkScrewDriver()
                .checkChisels()
                .countHowManyChiselProductsAreThere()
                .checkScrewDriver()
                .countEveryProduct()
                .validateNumOfProductsAreSumOfEachCategory();
    }

    @Test(priority = 3)
    public void removeFavouriteTest()
    {
          accountSteps
                  .goToHomePage();
          productSteps
                  .clickToggleNavLink();
          homeSteps
                  .goToMyFavouritesPage();
          favouritesSteps
                  .removeProductFromFavourites();
          productSteps
                  .clickToggleNavLink()
                  .signOut();
        accountSteps
                .goToSignInPage();
        signInSteps
                .validateWeAreOnCorrectPage()
                .validateWeHaveCorrectCredentials(testUser.getEmail(), testUser.getPassword())
                .enterEmail()
                .enterPassword()
                .clickLoginButton();
        productSteps
                .clickToggleNavLink();
        homeSteps
                .goToMyFavouritesPage();
        favouritesSteps
                .getTextFromFavourites()
                .validateThereAreNoProductsInFavourites();

    }

    @Test(priority = 4)
    public void TagsTest()
    {
        homeSteps
                .checkHummer()
                .getTagTextForFutureAssertion()
                .clickOnThorHummerProduct();
        productSteps
                .checkTagText()
                .validateTagsAreTheSame();
    }
}