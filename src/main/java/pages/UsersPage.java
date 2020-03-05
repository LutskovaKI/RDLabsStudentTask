package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageComponents.FilterUsersModalWindow;

import java.time.Duration;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

@Getter
@Slf4j
public class UsersPage extends BasePage {

    @FindBy(xpath = "//a[@data-tooltip='Filter']")
    private WebElementFacade filterButton;

    @FindBy(xpath = "//div[@id='status_inputfileddiv']//input")
    private WebElementFacade userStatusField;

    @FindBy(xpath = "//div[@id='adminroles_inputfileddiv']//input")
    private WebElementFacade userAdminRoleField;

    public void clickOnFilterButton() {
        log.info("Clicking on the [Filter button]");
        filterButton.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
        waitUntilSpinnerGone(3);
        filterButton.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible().waitUntilEnabled().waitUntilClickable().click();
    }
}
