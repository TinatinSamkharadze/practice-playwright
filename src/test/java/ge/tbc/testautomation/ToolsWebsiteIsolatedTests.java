package ge.tbc.testautomation;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.*;

public class ToolsWebsiteIsolatedTests {
    private Playwright playwright;
    private Browser browser;
    SoftAssert softAssert = new SoftAssert();
    private final Faker faker = new Faker();
    private String testEmail;
    private String testPassword;
    private String testFirstName;
    private String testLastName;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

    }

    private void registerNewUser(Page page) {
        page.navigate(PRACTICE_SOFTWARE_TESTING_URL);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register your account")).click();

        testFirstName = faker.name().firstName();
        testLastName = faker.name().lastName();
        String dob = "1967-07-11";
        String street = faker.address().streetAddress();
        String postcode = faker.address().zipCode();
        String city = faker.address().city();
        String state = faker.address().state();
        String phone = faker.phoneNumber().subscriberNumber(9);
        testEmail = faker.internet().emailAddress();
        String upper = faker.letterify("?").toUpperCase();
        String lower = faker.letterify("?").toLowerCase();
        String number = faker.numerify("#");
        String special = "@#$%&!".charAt(faker.random().nextInt(6)) + "";
        String rest = faker.lorem().characters(4, true);
        testPassword = new StringBuilder(upper + lower + number + special + rest).reverse().toString();

        page.getByPlaceholder("First name *").fill(testFirstName);
        page.getByPlaceholder("Your last name *").fill(testLastName);
        Locator dobField = page.getByLabel("Date of Birth *");
        dobField.click();
        dobField.fill(dob);
        page.getByPlaceholder("Your Street *").fill(street);
        page.getByPlaceholder("Your Postcode *").fill(postcode);
        page.getByPlaceholder("Your City *").fill(city);
        page.getByPlaceholder("Your State *").fill(state);
        page.locator("#country").selectOption("Italy");
        page.evaluate("window.scrollBy(0, 500)");
        page.getByPlaceholder("Your phone *").fill(phone);
        page.getByPlaceholder("Your email *").fill(testEmail);
        page.getByPlaceholder("Your password").fill(testPassword);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();

        page.waitForURL(Pattern.compile(".*/auth/login"));

        page.getByPlaceholder("Your email").fill(testEmail);
        page.getByPlaceholder("Your password").fill(testPassword);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.waitForURL(PRACTICE_SOFTWARE_TESTING_ACCOUNT_URL);
    }

    @Test()
    public void favouritesTest() {
        Page page = browser.newPage();
        registerNewUser(page);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
        page.locator("//img[@src='assets/img/products/pliers01.avif']").click();
        page.waitForSelector(".btn.btn-warning");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Favourites")).click();
        Locator alertMessage = page.locator(".toast-top-right.toast-container");
        alertMessage.waitFor();
        softAssert.assertTrue(alertMessage.isVisible());
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" " + testFirstName + " "+ testLastName + " ")).click();
        page.locator("//a[text()='Sign out']").click();

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        Locator email = page.getByPlaceholder("Your email");
        email.waitFor();
        email.fill(testEmail);
        Locator password = page.getByPlaceholder("Your password");
        password.waitFor();
        password.fill(testPassword);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.waitForURL(PRACTICE_SOFTWARE_TESTING_ACCOUNT_URL);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
        Locator sort = page.getByText(" Sort");
        softAssert.assertTrue(sort.isVisible());
        Locator card = page.locator("//img[@src='assets/img/products/pliers01.avif']");
        card.waitFor();
        card.click();
//        page.waitForTimeout(1000);
        Locator addToFavouritesButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Add to favourites "));
        softAssert.assertTrue(addToFavouritesButton.isVisible());
        addToFavouritesButton.click();
//        page.waitForTimeout(1000);
        alertMessage = page.locator("text=Product already in your favorites list");
        alertMessage.waitFor();
        softAssert.assertTrue(alertMessage.isVisible());
        softAssert.assertAll();
    }

    @Test()
    public void filterTest() {
        Page page = browser.newPage();
        registerNewUser(page);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
        page.getByLabel(" Screwdriver").click();
//        page.waitForTimeout(2000);
        int screwdriverCount = page.locator(".card").count();
        page.getByLabel(" Screwdriver").click();
        page.getByLabel(" Chisels").click();
//        page.waitForTimeout(2000);
        int chiselsCount = page.locator(".card").count();
        page.getByLabel(" Screwdriver").click();
//        page.waitForTimeout(2000);
        int combinedCount = page.locator(".card").count();
        assert combinedCount == screwdriverCount + chiselsCount;


    }

    @Test()
    public void removeFavouriteTest()
    {
        Page page = browser.newPage();
        registerNewUser(page);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" " + testFirstName + " "+ testLastName + " ")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My favorites")).click();
        page.locator(".svg-inline--fa.fa-xmark").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" " + testFirstName + " "+ testLastName + " ")).click();
        page.locator("//a[text()='Sign out']").click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByPlaceholder("Your email").fill(testEmail);
        page.getByPlaceholder("Your password").fill(testPassword);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.locator(".svg-inline--fa.fa-star").click();
        String expectedMessage = THERE_ARE_NO_FAVOURITES;
        String actualText = page.locator(".col").textContent();
        Assert.assertTrue(actualText.contains(expectedMessage));
    }

    @Test()
    public void TagsTest()
    {
        Page page = browser.newPage();
        registerNewUser(page);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
        page.getByLabel(" Hammer").click();
        page.getByAltText("Thor Hammer").click();
        assertThat(page.getByLabel("category")).hasText(HAMMER);
        assertThat(page.getByLabel("brand")).hasText(FORGE_FLEX_TOOLS);

    }
    @AfterMethod
    public void tearDown() {
      browser.close();
      playwright.close();
    }
}