package ge.tbc.testautomation.steps.telerik;

import ge.tbc.testautomation.pages.telerik.DemoPage;

public class DemoStep {
    private final DemoPage demoPage;

    public DemoStep(DemoPage demoPage) {
        this.demoPage = demoPage;
    }

    public DemoStep clickPricingPageLink()
    {
        demoPage.pricingPageLink.click();
        return this;
    }
}
