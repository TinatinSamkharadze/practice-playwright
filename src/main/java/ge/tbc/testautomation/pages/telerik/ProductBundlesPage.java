package ge.tbc.testautomation.pages.telerik;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductBundlesPage extends DemoPage{

    public Locator devcraftUi;

    public ProductBundlesPage(Page page) {
        super(page);
        this.devcraftUi = page.locator(".Purchase-cell--DevCraftUI");
    }

}
