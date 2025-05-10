package ge.tbc.testautomation;

import ge.tbc.testautomation.pages.telerik.DemoPage;
import ge.tbc.testautomation.steps.telerik.DemoStep;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.TELERIK_URL;

public class PlaywrightTests extends BaseTest {
    private DemoPage demoPage;
    private DemoStep demoSteps;
    @BeforeMethod
    public void setUpTest() {
        demoPage = new DemoPage(page);
        demoSteps = new DemoStep(demoPage);
    }

    @Test
    public void validateBundleOffers()
    {
        navigateTo(TELERIK_URL);
        demoSteps
                .clickPricingPageLink();
    }
}
