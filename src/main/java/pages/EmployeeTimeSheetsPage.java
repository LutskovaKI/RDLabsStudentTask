package pages;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
public class EmployeeTimeSheetsPage extends BasePage{

    @FindBy(id = "x_report_employeeId_empName")
    private WebElementFacade searchInputField;

    @FindBy(css = ".ac_results li")
    private WebElementFacade employeeNameAutoCompleteElement;

    public void switchToFrame(){
        getDriver().switchTo().frame("noncoreIframe");
    }
}
