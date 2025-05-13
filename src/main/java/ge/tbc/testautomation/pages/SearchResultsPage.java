package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultsPage {
   public Locator searchResultImages;

    public SearchResultsPage(Page page)
    {
        this.searchResultImages  = page.locator(".product-image-container");
    }
}
