package pages;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.Duration;

@Getter
public class WorkShiftPage extends BasePage {

    @FindBy(xpath = "//div[@data-tooltip='Add Work Shift']//a")
    private WebElementFacade addWorkShiftButton;

    @FindBy(css = "#modal1.open")
    private WebElementFacade addWorkShiftWindow;

    @FindBy(css = ".picker--opened .picker__box")
    WebElementFacade timePickerLocator;

    @FindBy(css = ".ng-pristine .primary-btn")
    WebElementFacade saveAddWorkShiftButton;

    @FindBy(css = "[form] [ng-repeat='item in form\\.items']:nth-of-type(1) .input-field")
    WebElementFacade errorMessageUnderAddWorkShiftField;

    public void clickOnSaveAddWorkShiftButton() {
        saveAddWorkShiftButton.waitUntilVisible().waitUntilEnabled().waitUntilClickable().click();
    }

    public String getDefaultErrorMessageText() {
        return errorMessageUnderAddWorkShiftField.waitUntilVisible().waitUntilEnabled().getText();
    }
}
