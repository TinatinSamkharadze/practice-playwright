package ge.tbc.testautomation.pages.toolwebsite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FavouritesPage {
    public Locator removeBtn,
    emptyFavourites;
    public FavouritesPage(Page page)
    {
        this.removeBtn = page.locator(".svg-inline--fa.fa-xmark");
        this.emptyFavourites =  page.locator(".col");
    }
}
