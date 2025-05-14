package ge.tbc.testautomation.pages.toolwebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {
    public Locator signInLink,
    firstProduct,
    screwdriver,
    chisels,
    products,
    hummer,
    thor_hummer,
    myFavourites;
    public HomePage (Page page)
    {
        this.signInLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in"));
        this.firstProduct = page.locator(".card-img-top").first();
        this.screwdriver = page.getByLabel(" Screwdriver");
        this.chisels =page.getByLabel(" Chisels");
        this.products = page.locator(".card");
        this.hummer = page.getByLabel("Hammer");
        this.thor_hummer = page.getByAltText("Thor Hammer");
        this.myFavourites = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My favorites"));

    }
}
