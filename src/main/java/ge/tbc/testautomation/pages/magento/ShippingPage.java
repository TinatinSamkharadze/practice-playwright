package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ShippingPage {
    public Locator streetAddress,
            city,
            postalCode,
            phoneInput,
            shippingMethod,
            nextBtn,
            checkoutBtn,
            state;

    public ShippingPage(Page page) {
        this.streetAddress = page.getByLabel("Street Address: Line 1");
        this.city = page.getByLabel("City");
        this.postalCode = page.getByLabel("Zip/Postal Code");
        this.phoneInput = page.getByLabel("Phone Number");
        this.shippingMethod = page.locator(".radio").last();
        this.nextBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
        this.checkoutBtn = page.locator(".action.primary.checkout").last();
        this.state = page.getByLabel("State/Province").first();
    }
}
