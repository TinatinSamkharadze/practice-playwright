package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignUpPage {

    public Locator firstName,
    lastName,
    email,
    password,
    confirmPasswordField,
    submitBtn;

    public SignUpPage(Page page) {
        this.firstName  = page.getByLabel("First Name");
        this.lastName =  page.getByLabel("Last Name");
        this.email = page.getByLabel("Email").first();
        this.password = page.getByLabel("Password").first();
        this.confirmPasswordField =  page.locator("#password-confirmation");
        this.submitBtn = page.locator(".action.submit.primary");



    }
}
