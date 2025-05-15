package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PaymentPage {
    public Locator cartSubtotal,
    shippingPrice,
    totalPrice,
    discountCodeLink,
    discountCodeField,
    discountBtn,
            alert,
    shippingContent,
    placeOrder;
    public PaymentPage (Page page)
    {
        this.cartSubtotal = page.locator(".amount").first();
        this.shippingPrice = page.locator(".totals.shipping.excl");
        this.totalPrice = page.locator(".grand.totals");
        this.discountCodeLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Apply Discount Code"));
        this.discountCodeField = page.locator("#discount-code");
        this.discountBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Discount"));
        this.alert = page.getByRole(AriaRole.ALERT);
        this.shippingContent = page.locator(".shipping-information-content").first();
        this.placeOrder = page.getByText("Place Order");


    }
}
