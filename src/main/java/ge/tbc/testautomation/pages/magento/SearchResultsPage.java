package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultsPage {
   public Locator searchResultImages;
   public Locator reviewCountLink;

    public SearchResultsPage(Page page)
    {
        this.searchResultImages  = page.locator(".product-image-container");
        this.reviewCountLink   = page.locator(".reviews-actions").first();
    }
}
