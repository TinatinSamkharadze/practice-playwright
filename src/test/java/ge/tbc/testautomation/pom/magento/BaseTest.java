package ge.tbc.testautomation.pom.magento;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.models.UserCredentials;
import ge.tbc.testautomation.steps.magento.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static ge.tbc.testautomation.data.Constants.MAGENTO_URL;

public class BaseTest {
    public SoftAssert softAssert;
    public Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;
    public UserCredentials creds;
    public HomeSteps homeSteps;
    public SearchResultsSteps searchResultsSteps;
    public ItemsSteps itemsSteps;
    public ReviewsSteps reviewsSteps;
    public SignUpSteps signUpSteps;
    public WishlistSteps wishlistSteps;
    public SignInSteps signInSteps;
    public MyAccountSteps myAccountSteps;
    public ShippingSteps shippingSteps;
    public PaymentSteps paymentSteps;
    public SuccessSteps successSteps;

    @BeforeClass
    @Parameters({"browserType"})
    public void setUp(@Optional("chromium") String browserType){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(true);

        if (browserType.equalsIgnoreCase("chromium")){
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserType.equalsIgnoreCase("safari")) {
            browser = playwright.webkit().launch(launchOptions);
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        creds = new UserCredentials();
    }

    @BeforeMethod
    public void resetContext() {
        homeSteps = new HomeSteps(page);
        searchResultsSteps = new SearchResultsSteps(page);
        itemsSteps = new ItemsSteps(page);
        reviewsSteps = new ReviewsSteps(page);
        signUpSteps = new SignUpSteps(page);
        wishlistSteps = new WishlistSteps(page);
        page.navigate(MAGENTO_URL);
        this.signInSteps = new SignInSteps(page);
        this.myAccountSteps = new MyAccountSteps(page);
        this.shippingSteps = new ShippingSteps(page);
        this.paymentSteps = new PaymentSteps(page);
        this.successSteps = new SuccessSteps(page);
        this.softAssert = new SoftAssert();
    }


    @AfterClass
    public void tearDown() {
        browserContext.close();
        browser.close();
        playwright.close();
    }


    @AfterMethod
    public void tearDownPerTest() {
        softAssert.assertAll();
    }
}