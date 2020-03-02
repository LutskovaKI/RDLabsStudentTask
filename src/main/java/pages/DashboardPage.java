package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

@Getter
@Slf4j
public class DashboardPage extends BasePage {

    public static final String PAGE_TITLE = "OrangeHRM";

    @FindBy(css = "#account-name")
    private WebElementFacade accountNameLabel;

    @FindBy(xpath = "//a[@id='side-menu-hamburger']")
    private WebElementFacade hideMenuButton;

    @FindBy(css = "#dashboard__viewNewsOnDashboard")
    private WebElementFacade newsContainer;

    @FindBy(css = ".dashboard-outline .l6:nth-of-type(5)")
    private WebElementFacade newsSection;

    @FindBy(css = ".l6:nth-of-type(5) .dashboardCard-title-for-card")
    private WebElementFacade newsHeader;

    @FindBy(xpath = "//div[contains(@id,'dashboard__viewNewsOnDashboard')]//div[@class = 'inner']//ul//li")
    private List<WebElementFacade> listOfNews;

    @FindBy(css = "#dashboard__viewNewsOnDashboard > div.document-count-text > div.right")
    private WebElementFacade realAmountOfNews;

    @FindBy(css = "#dashboard__viewDocumentsOnDashboard")
    private WebElementFacade documentsContainer;

    @FindBy(css = ".material-icons.moreIcon.right")
    private WebElementFacade threeDotsButtonEmployeeDistribution;

    @FindBy(css = ".l6:nth-of-type(3) .card-content .material-icons")
    private WebElementFacade threeDotsButtonLeaveTaken;

    @FindBy(css = "#task-list-group-panel-menu_holder-legend")
    private WebElementFacade employeeLegend;

    @FindBy(css = "#legend")
    private WebElementFacade leavesLegend;

    public void clickOnHideMenuButton() {
        log.info("Clicking on the [Hide menu] button");
        hideMenuButton.waitUntilVisible().waitUntilClickable().click();
    }

    public boolean checkNewsSectionIsPresent(){
        return newsSection.waitUntilVisible().isDisplayed();
    }

    public String getNewsSectionHeaderName() {
       return newsHeader.waitUntilVisible().getText();
    }

    public int newsCounter(){
        int count = 0;
        List<WebElementFacade> listOfNews = getListOfNews();
        for (int i = 0; i < listOfNews.size(); i++) {
            WebElementFacade webElementFacade = listOfNews.get(i);   // element = 0;  dashboardPage.getListOfNews = 16;  element = 0   temo = 56;
            count++;
        }
        return count;
    }

    public int getRealAmountOfNews(){
        return Integer.parseInt(realAmountOfNews.getText().split("/")[1].trim());
    }
}
