package ge.tbc.testautomation.steps.toowebsite;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import ge.tbc.testautomation.pages.toolwebsite.AccountPage;
import org.testng.asserts.SoftAssert;

public class AccountSteps {
    Page page;
    AccountPage accountPage;
    SoftAssert softAssert;

    public AccountSteps (Page page)
    {
        this.page = page;
        this.accountPage = new AccountPage(page);
        this.softAssert = new SoftAssert();
    }

    public AccountSteps goToHomePage()
    {
        accountPage.homePageLink.click();
        return this;
    }

    public AccountSteps validateWeAreOnRightPage() {
       page.reload();
       return this;
    }

    public AccountSteps goToSignInPage()
    {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        return this;
    }

    public AccountSteps goToFavourites()
    {
        accountPage.favourites.click();
        return this;
    }
}
