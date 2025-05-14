package ge.tbc.testautomation.steps.toowebsite;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.toolwebsite.HomePage;
import org.testng.asserts.SoftAssert;

public class HomeSteps {
    Page page;
    HomePage homePage;
    private int screwdriverCount;
    private int chiselsCount;
    private int combinedCount;
    SoftAssert softAssert;
    private String actualTag;
    public HomeSteps(Page page)
    {
        this.page = page;
        this.homePage = new HomePage(page);
        this.softAssert = new SoftAssert();
    }

    public HomeSteps goToSignInPage()
    {
        homePage.signInLink.click();
        return this;
    }

    public HomeSteps clickOnFirstProduct()
    {
        homePage.firstProduct.waitFor();
        homePage.firstProduct.click();
        return this;
    }

    public HomeSteps checkScrewDriver()
    {
        homePage.screwdriver.waitFor();
        homePage.screwdriver.click();
        return this;
    }

    public HomeSteps checkChisels()
    {
        homePage.chisels.click();
        return this;
    }

    public int getScrewdriverCount()
    {
        return this.screwdriverCount;
    }

    public HomeSteps countHowManyScrewDriverProductsAreThere()
    {
        homePage.firstProduct.waitFor();
        this.screwdriverCount = homePage.products.count();
        return this;
    }

    public int getChiselsCount()
    {
        return this.chiselsCount;
    }

    public HomeSteps countHowManyChiselProductsAreThere()
    {
        homePage.firstProduct.waitFor();
        this.chiselsCount = homePage.products.count();
        return this;
    }

    public int getCombinedCount()
    {
        return this.combinedCount;
    }

    public HomeSteps countEveryProduct()
    {
        this.combinedCount = homePage.products.count();
         return this;
    }

    public HomeSteps validateNumOfProductsAreSumOfEachCategory()
    {
        softAssert.assertEquals(getCombinedCount(), getScrewdriverCount() + getChiselsCount() + getScrewdriverCount());
        return this;
    }

    public HomeSteps checkHummer()
    {
        homePage.hummer.click();
        return this;
    }

    public HomeSteps clickOnThorHummerProduct()
    {
        homePage.thor_hummer.waitFor();
        homePage.thor_hummer.click();
        return this;
    }

    public String getActualTag()
    {
        return this.actualTag;
    }

    public HomeSteps getTagTextForFutureAssertion()
    {
        this.actualTag = homePage.hummer.textContent().trim();
        return this;
    }

    public HomeSteps goToMyFavouritesPage()
    {
        homePage.myFavourites.click();
        return this;
    }
}
