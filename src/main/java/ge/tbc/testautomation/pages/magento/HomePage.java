package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {
    public Locator searchBarComboBox;
   public  Locator hotSellerFirstProductImage;
   public Locator topNavigationBar;
   public  Locator signInNavLink;
   public Locator createAccountNavLink;
    public Locator navToggleButton;
   public Locator mobileMenuLink;
   public Locator accountMenuLink;
   public Locator allOffersInHotSeller;

    public HomePage(Page page)
    {
        this.searchBarComboBox = page.getByRole(AriaRole.COMBOBOX);
        this.hotSellerFirstProductImage  = page.locator(".product-image-container").first();
        this.topNavigationBar  = page.locator("#ui-id-2");
        this.signInNavLink  = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In"));
        this.createAccountNavLink  = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create an Account"));
        this.navToggleButton= page.locator(".action.nav-toggle");
        this.mobileMenuLink  = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Menu"));
        this.accountMenuLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Account"));
        this.allOffersInHotSeller =  page.locator(".product-item");
    }

    public Locator getColorOptions(Locator offer) {
        return offer.locator(".swatch-option.color");
    }

    public Locator getProductImage(Locator offer) {
        return offer.locator("img.product-image-photo");
    }
}
