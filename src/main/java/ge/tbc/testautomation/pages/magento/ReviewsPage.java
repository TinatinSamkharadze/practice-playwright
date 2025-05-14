package ge.tbc.testautomation.pages.magento;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ReviewsPage {
   public Locator reviewItems;


    public ReviewsPage (Page page)
    {
       this.reviewItems  = page.locator(".item.review-item");
    }
}
