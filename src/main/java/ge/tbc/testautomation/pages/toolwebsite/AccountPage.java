package ge.tbc.testautomation.pages.toolwebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountPage {

    public Locator homePageLink,
    favourites;
    public AccountPage(Page page)
    {
        this.homePageLink =  page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
        this.favourites = page.locator(".btn.btn-outline-secondary.d-flex").first();
    }
}
