package ge.tbc.testautomation;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.modles.UserCredentials;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.Random;

public class BaseTest {
   public Random random = new Random();
   public Faker faker = new Faker();
  public  SoftAssert softAssert = new SoftAssert();
   public Playwright playwright;
   public  Browser browser;
   public  BrowserContext browserContext;
    public Page page;
    public UserCredentials creds;
    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
        creds = new UserCredentials();
    }

    @AfterClass
    public void tearDown(){
        browserContext.close();
        browser.close();
        playwright.close();
    }



    @AfterMethod
    public void tearDownPerTest() {
        softAssert.assertAll();
    }
}