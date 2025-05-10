package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.nio.file.Paths;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @Parameters({"browser", "headless"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType, @Optional("true") String headless) {
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(headless));

        switch (browserType.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "webkit":
                browser = playwright.webkit().launch(launchOptions);
                break;
            default:
                browser = playwright.chromium().launch(launchOptions);
        }

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        page = context.newPage();

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        System.out.println("Starting test: " + method.getName());
    }

    @AfterMethod
    public void afterMethod(Method method) {
        System.out.println("Finished test: " + method.getName());

        if (context != null) {
            String screenshotPath = "screenshots/" + method.getName() + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    protected void navigateTo(String url) {
        page.navigate(url);
    }

    protected void maximizeWindow() {
        page.setViewportSize(1920, 1080);
    }
}