package ge.tbc.testautomation.steps.toowebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.toolwebsite.ProductPage;
import org.testng.asserts.SoftAssert;

public class ProductSteps {
    Page page;
    ProductPage productPage;
    private String expectedTagText;
    HomeSteps homeSteps;
    SoftAssert softAssert;

    public ProductSteps(Page page)
    {
        this.page = page;
        this.productPage = new ProductPage(page);
        this.homeSteps = new HomeSteps(page);
        this.softAssert = new SoftAssert();
    }

    public ProductSteps clickAddToFavouritesBtn()
    {
        productPage.addToFavouritesBtn.waitFor();
        productPage.addToFavouritesBtn.click();
        return this;
    }

    public String getExpectedTagText()
    {
        return this.expectedTagText;
    }

    public ProductSteps checkTagText()
    {
        this.expectedTagText = productPage.categoryTag.textContent().trim();
        return this;
    }

    public ProductSteps validateTagsAreTheSame()
    {
        softAssert.assertEquals(homeSteps.getActualTag(), getExpectedTagText());
        return this;
    }

    public ProductSteps validateSuccessMessage()
    {
        Locator alertMessage = productPage.alertMessage;
        alertMessage.waitFor();
        softAssert.assertTrue(alertMessage.isVisible());
        return this;
    }

    public ProductSteps clickToggleNavLink()
    {
        productPage.toggleNavLink.click();
        return this;
    }

    public ProductSteps signOut()
    {
        productPage.signOut.click();
        return this;
    }

    public ProductSteps validateProductIsAlreadyInFavourites()
    {
        softAssert.assertTrue(productPage.errorMessage.isVisible());
         return this;
    }
}
