package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class WishlistPage {
    public Locator wishlistSuccessAlert;
    public  Locator welcomeBanner;

    public WishlistPage (Page page)
    {
        this.wishlistSuccessAlert = page.getByRole(AriaRole.ALERT).first();
        this.welcomeBanner  = page.locator(".logged-in").first();
    }
}
