package ge.tbc.testautomation.steps.magento;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.models.UserCredentials;
import ge.tbc.testautomation.pages.magento.SignUpPage;

public class SignUpSteps {
    Faker faker = new Faker();
    Page page;
    SignUpPage signUpPage;
    private String testPassword;
    private String testEmail;
    public String testFirstName;
    public String testLastName;
    private final UserCredentials credentials = new UserCredentials();

    public SignUpSteps(Page page)
    {
        this.page = page;
        this.signUpPage = new SignUpPage(page);
    }

    public SignUpSteps enterFirstName() {
        credentials.firstName = faker.name().firstName();
        signUpPage.firstName.waitFor();
        signUpPage.firstName.fill(credentials.firstName);
        return this;
    }

    public SignUpSteps enterLastName() {
        credentials.lastName = faker.name().lastName();
        signUpPage.lastName.waitFor();
        signUpPage.lastName.fill(credentials.lastName);
        return this;
    }


    public SignUpSteps enterEmail() {
        credentials.email = faker.internet().emailAddress();
        signUpPage.email.waitFor();
        signUpPage.email.fill(credentials.email);
        return this;
    }


    public SignUpSteps enterPassword() {
        credentials.password = generatePassword();
        signUpPage.password.waitFor();
        signUpPage.password.fill(credentials.password);
        return this;
    }

    public SignUpSteps confirmPassword() {
        signUpPage.confirmPasswordField.fill(credentials.password);
        signUpPage.confirmPasswordField.waitFor();
        return this;
    }

    public SignUpSteps clickSubmitBtn()
    {
        signUpPage.submitBtn.scrollIntoViewIfNeeded();
        signUpPage.submitBtn.waitFor();
        signUpPage.submitBtn.click();
        return this;
    }

    public String getTestEmail() {
        return testEmail;
    }

    public String getTestPassword() {
        return testPassword;
    }

    private String generatePassword() {
        String upper = faker.letterify("?").toUpperCase();
        String lower = faker.letterify("?").toLowerCase();
        String number = faker.numerify("#");
        String special = "@#$%&!".charAt(faker.random().nextInt(6)) + "";
        String rest = faker.lorem().characters(4, true);
        return new StringBuilder(upper + lower + number + special + rest).reverse().toString();
    }

    public UserCredentials getCredentials() {
        return credentials;
    }
    public SignUpSteps saveCredentialsTo(UserCredentials destination) {
        destination.email = credentials.email;
        destination.password = credentials.password;
        destination.firstName = credentials.firstName;
        destination.lastName = credentials.lastName;
        return this;
    }


}
