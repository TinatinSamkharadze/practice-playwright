package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MyAccountPage {

    public Locator myWishListLink;
    public MyAccountPage (Page page)
    {
        this.myWishListLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Wish List"));
    }
}
