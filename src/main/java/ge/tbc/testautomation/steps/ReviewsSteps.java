package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ReviewsPage;
import org.testng.Assert;

public class ReviewsSteps {
    Page page;
    ReviewsPage reviewsPage;
    private int actualReviewCount;
    ItemsSteps itemsSteps;

    public ReviewsSteps (Page page)
    {
        this.page = page;
        this.reviewsPage = new ReviewsPage(page);
        this.itemsSteps = new ItemsSteps(page);
    }

    public ReviewsSteps countHowManyReviewsAreThere()
    {
        Locator numberOfReviews = reviewsPage.reviewItems;
        numberOfReviews.first().waitFor();
        this.actualReviewCount = numberOfReviews.count();
        return this;
    }

    public int getActualReviewCount()
    {
        return this.actualReviewCount;
    }
    public ReviewsSteps waitForReviewsToAppear()
    {
        reviewsPage.reviewItems.first().waitFor();
        return this;
    }
    public ReviewsSteps validateNumberOfReviewsAreTheSame()
    {
        Assert.assertEquals(itemsSteps.getExpectedReviewCount(), getActualReviewCount());
        return this;
    }
}
