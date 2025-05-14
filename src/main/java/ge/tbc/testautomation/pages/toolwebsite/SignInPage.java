package ge.tbc.testautomation.pages.toolwebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignInPage {

    public Locator signUpPageLink;
    public Locator loginEmailInput;
    public Locator loginPasswordInput;
    public Locator loginButton;

    public SignInPage(Page page)
    {
        this.signUpPageLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register your account"));
        this.loginEmailInput = page.getByPlaceholder("Your email");
        this.loginPasswordInput = page.getByPlaceholder("Your password");
        this.loginButton = page.getByText("Login").last();
    }
}
