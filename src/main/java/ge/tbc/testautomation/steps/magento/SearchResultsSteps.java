package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.magento.SearchResultsPage;

public class SearchResultsSteps {
    Page page;
    SearchResultsPage searchResultsPage;

    public SearchResultsSteps(Page page)
    {
        this.page = page;
        this.searchResultsPage = new SearchResultsPage(page);
    }

    public SearchResultsSteps locateFirstSearchResult()
    {
        searchResultsPage.searchResultImages.first().waitFor();
        return this;
    }

    public SearchResultsSteps goToItemsPage()
    {
        searchResultsPage.searchResultImages.first().click();
        return this;
    }


}
