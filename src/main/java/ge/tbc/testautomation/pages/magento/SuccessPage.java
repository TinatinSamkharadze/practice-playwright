package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SuccessPage {
    public Locator successMessage;
    public SuccessPage(Page page)
    {
        this.successMessage = page.locator(".checkout-success");
    }
}
