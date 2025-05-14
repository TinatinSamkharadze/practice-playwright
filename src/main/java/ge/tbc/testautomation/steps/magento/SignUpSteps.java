package ge.tbc.testautomation.steps.magento;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.magento.SignUpPage;

public class SignUpSteps {
    Faker faker = new Faker();
    Page page;
    SignUpPage signUpPage;
    private String testPassword;
    private String testEmail;
    public String testFirstName;
    public String testLastName;

    public SignUpSteps(Page page)
    {
        this.page = page;
        this.signUpPage = new SignUpPage(page);
    }

    public SignUpSteps enterFirstName()
    {
        testFirstName = faker.name().firstName();
        signUpPage.firstName.fill(testFirstName);
        return this;
    }

    public SignUpSteps enterLastName()
    {
        testLastName = faker.name().lastName();
        signUpPage.lastName.fill(testLastName);
        return this;
    }

    public SignUpSteps enterEmail()
    {
        testEmail = faker.internet().emailAddress();
        signUpPage.email.fill(testEmail);
        return this;
    }

    public SignUpSteps enterPassword()
    {
        String upper = faker.letterify("?").toUpperCase();
        String lower = faker.letterify("?").toLowerCase();
        String number = faker.numerify("#");
        String special = "@#$%&!".charAt(faker.random().nextInt(6)) + "";
        String rest = faker.lorem().characters(4, true);
        testPassword = new StringBuilder(upper + lower + number + special + rest).reverse().toString();
        signUpPage.password.fill(testPassword);
        return this;
    }

    public SignUpSteps confirmPassword()
    {
        signUpPage.confirmPasswordField.fill(testPassword);
        return this;
    }

    public SignUpSteps clickSubmitBtn()
    {
        signUpPage.submitBtn.scrollIntoViewIfNeeded();
        signUpPage.submitBtn.click();
        return this;
    }
}
