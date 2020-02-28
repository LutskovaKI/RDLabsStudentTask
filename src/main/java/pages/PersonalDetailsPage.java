package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
@Slf4j
public class PersonalDetailsPage extends BasePage {

    @FindBy(css = "#personal_details_tab h4")
    private WebElementFacade personalDetailsHeader;

    @FindBy(css = "#emp_birthday")
    private WebElementFacade dateOfBirthInputField;

    @FindBy(xpath = "//div[@id='nation_code_inputfileddiv']//input")
    private WebElementFacade nationalitySelect;

    @FindBy(xpath = "//div[@id='eeo_race_ent_inputfileddiv']//input")
    private WebElementFacade eeoRaceAndEthnicitySelect;

    @FindBy(xpath = "//label[@for='emp_gender_1']")
    private WebElementFacade maleRadioButton;

    @FindBy(xpath = "//label[@for='emp_gender_2']")
    private WebElementFacade femaleRadioButton;

    @FindBy(xpath = "//form[@id='pimPersonalDetailsForm']/materializecss-decorator[8]//sf-decorator//button[@type='submit']")
    private WebElementFacade savePersonalDetailsButton;

    @FindBy(xpath = "//span[@class='help-block-message']")
    private WebElementFacade errorMessageAfterTomorrowDateOfBirth;


    public void enterDateOfBirth(String date) {
        log.info(String.format("Putting %s date into [Date of birth] field", date));
        dateOfBirthInputField.clear();
        dateOfBirthInputField.waitUntilEnabled().sendKeys(date);
    }

    public void clickOnMaleRadioButton() {
        log.info("set Male radio button checked");
        maleRadioButton.waitUntilVisible().waitUntilClickable().click();
    }

    public void clickOnFemaleRadioButton() {
        log.info("set Female radio button checked");
        femaleRadioButton.waitUntilVisible().waitUntilClickable().click();
    }

    public boolean getMaleRadioButtonAttribute() {
        log.info("get string male radio button attribute and convert to boolean");
        return Boolean.parseBoolean(maleRadioButton.waitUntilEnabled().getAttribute("checked"));
    }

    public boolean getFemaleRadioButtonAttribute() {
        log.info("get string female radio button attribute and convert to boolean");
        return Boolean.parseBoolean(femaleRadioButton.waitUntilEnabled().getAttribute("checked"));
    }

    public void clickOnSavePersonalDetailsButton() {
        savePersonalDetailsButton.waitUntilVisible().waitUntilClickable().click();
    }

}
