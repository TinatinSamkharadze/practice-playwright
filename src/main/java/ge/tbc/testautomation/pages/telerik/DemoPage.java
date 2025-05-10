package ge.tbc.testautomation.pages.telerik;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DemoPage {
    private final Page page;

    public Locator pricingPageLink;


    public DemoPage(Page page) {
        this.page = page;
        this.pricingPageLink = page.locator("a[href='/purchase.aspx']").first();
    }
}
