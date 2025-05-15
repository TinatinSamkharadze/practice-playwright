package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignInPage {
    public Locator email,
            password,
            signIn,
            pageTitle;

    public SignInPage(Page page) {
        this.email = page.locator("#email");
        this.password = page.locator("#pass").first();
        this.signIn = page.locator(".action.login.primary");
        this.pageTitle = page.getByText("Customer Login");
    }
}
