package ge.tbc.testautomation.pom.toolwebsite.sharedcontext;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.toowebsite.*;
import ge.tbc.testautomation.util.TestUser;
import ge.tbc.testautomation.util.TestUserFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static ge.tbc.testautomation.data.Constants.PRACTICE_SOFTWARE_TESTING_URL;

public class SharedBaseTest {
    public SoftAssert softAssert;
    public Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;
    public SignInSteps signInSteps;
    public AccountSteps accountSteps;
    public HomeSteps homeSteps;
    public ProductSteps productSteps;
    public TestUser testUser;
    public FavouritesSteps favouritesSteps;


    @BeforeClass
    @Parameters({"browserType"})
    public void setUp(@Optional("chromium") String browserType) {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(true);

        if (browserType.equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserType.equalsIgnoreCase("safari")) {
            browser = playwright.webkit().launch(launchOptions);
        }

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
        this.softAssert = new SoftAssert();
        page.navigate(PRACTICE_SOFTWARE_TESTING_URL);

    }

    @AfterMethod
    public void cleanupPage() {
        if (page != null) {
            page.close();
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
