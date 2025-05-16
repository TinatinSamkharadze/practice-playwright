package ge.tbc.testautomation.steps.toowebsite;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.toolwebsite.FavouritesPage;
import org.testng.Assert;

import static ge.tbc.testautomation.data.Constants.THERE_ARE_NO_FAVOURITES;

public class FavouritesSteps {
    Page page;
    FavouritesPage favouritesPage;
    private String actualText;

    public FavouritesSteps (Page page)
    {
        this.page = page;
        this.favouritesPage = new FavouritesPage(page);
    }

    public FavouritesSteps removeProductFromFavourites()
    {
        favouritesPage.removeBtn.waitFor();
        favouritesPage.removeBtn.click();
        return this;
    }

    public String getActualText()
    {
        return actualText;
    }

    public FavouritesSteps validateThereAreNoProductsInFavourites()
    {
        Assert.assertTrue(getActualText().contains(THERE_ARE_NO_FAVOURITES));
        return this;
    }

    public FavouritesSteps getTextFromFavourites()
    {
        this.actualText = favouritesPage.emptyFavourites.textContent();
        return this;
    }

}
