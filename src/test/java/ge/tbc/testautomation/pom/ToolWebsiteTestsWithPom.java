package ge.tbc.testautomation.pom;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.toowebsite.*;
import ge.tbc.testautomation.util.TestUser;
import ge.tbc.testautomation.util.TestUserFactory;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.PRACTICE_SOFTWARE_TESTING_URL;

public class ToolWebsiteTestsWithPom {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    SignInSteps signInSteps;
    AccountSteps accountSteps;
    HomeSteps homeSteps;
   ProductSteps productSteps;
    private TestUser testUser;
    FavouritesSteps favouritesSteps;


    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        browserContext = browser.newContext();
        testUser = TestUserFactory.registerNewUser(browserContext);
    }

    @BeforeMethod
    public void testSetup() {
        page = browserContext.newPage();
        this.signInSteps = new SignInSteps(page);
        this.accountSteps = new AccountSteps(page);
        this.homeSteps = new HomeSteps(page);
        this.productSteps = new ProductSteps(page);
        this.favouritesSteps = new FavouritesSteps(page);
        page.navigate(PRACTICE_SOFTWARE_TESTING_URL);

    }

    @AfterMethod
    public void cleanupPage() {
        if (page != null) {
            page.close();
        }
    }

    @AfterClass
    public void tearDown() {
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

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