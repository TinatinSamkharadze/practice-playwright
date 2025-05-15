package ge.tbc.testautomation.steps.magento;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.magento.SuccessPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SuccessSteps {
    Page page;
    SuccessPage successPage;

    public SuccessSteps(Page page)
    {
        this.page = page;
        this.successPage = new SuccessPage(page);
    }

    public SuccessSteps validateOrderNumberIsDisplayed() {
        Pattern pattern = Pattern.compile(".*\\d+.*");
        assertThat(successPage.successMessage).hasText(pattern);
        return this;
    }

    public SuccessSteps validatePageTitleIsSuccess() {
        assertThat(page).hasTitle("Success Page");
        return this;
    }
}
