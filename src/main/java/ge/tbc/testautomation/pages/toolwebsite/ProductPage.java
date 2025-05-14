package ge.tbc.testautomation.pages.toolwebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductPage {
    public Locator addToFavouritesBtn;
    public Locator categoryTag,
    alertMessage,
    toggleNavLink,
    signOut,
    errorMessage;
    public ProductPage(Page page)
    {
        this.addToFavouritesBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Favourites"));
        this.categoryTag = page.getByLabel("category");
        this.alertMessage =  page.locator(".toast-top-right.toast-container");
        this.toggleNavLink = page.locator("#menu");
        this.signOut = page.locator("//a[text()='Sign out']");
        this.errorMessage = page.locator("text=Product already in your favorites list");




    }
}
