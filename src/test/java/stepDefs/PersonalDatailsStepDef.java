package stepDefs;

import com.google.common.collect.Ordering;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import steps.DefaultStepsData;
import steps.PersonalDetailsSteps;

import java.util.List;

import static utils.DateUtils.*;
import static utils.SessionVariables.DATE_OF_BIRTH;

public class PersonalDatailsStepDef extends DefaultStepsData {

    @Steps
    PersonalDetailsSteps personalDetailsSteps;

    @Then("I save current Date of Birth to session")
    public void saveCurentDateOfBirthToSession() {
        DATE_OF_BIRTH.put(personalDetailsSteps.getValueFromDateOfBirthField());
    }

    @When("I change Date of Birth added 1 day to old date")
    public void changeDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_OrangeHRM, 1, currentDate);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("Date of Birth field contains old date (date from session)")
    public void checkThatDateOfBirthNotChange() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        softly.assertThat(currentDate).as("After refreshing Date of Birth change").isEqualTo(DATE_OF_BIRTH.get());
    }

    @Then("I check that all countries in Nationality select box ordered by name asc")
    public void checkOrderingInNationalitySelectBox() {
        List<String> optionsFromNationalitySelect = personalDetailsSteps.getOptionsFromNationalitySelect();
        boolean isSorted = Ordering.natural().isOrdered(optionsFromNationalitySelect);
        softly.assertThat(isSorted).as("Wrong ordering inside select box").isTrue();
    }

    @When("I click on $gender radio button")
    public void setGenderRadioButtonAsChecked(String gender){
        personalDetailsSteps.checkGenderButton(gender);
    }

    @Then("$gender radio button is $condition")
    public void checkGenderRadioButtonIsUnchecked(String gender, String condition) {
        if (condition.contains("not")) {
            softly.assertThat(personalDetailsSteps.checkSelectedGenderRadioButton(gender)).isFalse();
        } else {
            softly.assertThat(personalDetailsSteps.checkSelectedGenderRadioButton(gender)).isTrue();
        }
    }

    @When("I set Date of Birth as tomorrow date")
    public void setTomorrowDateOfBirth() {
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_OrangeHRM, 1);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("I click on Save button in Personal Details form")
    public void saveTomorrowBirthDate() {
        personalDetailsPage.clickOnSavePersonalDetailsButton();
    }

    @Then("I check that error message with text $errorText appears under Date of Birth field")
    public void checkErrorMessageAfterTomorrowDateOfBirth(String errorTextMessage) {
        softly.assertThat(personalDetailsPage.getErrorMessageAfterTomorrowDateOfBirth().waitUntilVisible().getText()).as("Wrong message is shown")
                .contains(errorTextMessage);
    }

    @When ("I check that EEO Race and Ethnicity select has NO value by default")
    public void checkEEORaceAndEthnicityFieldHasNoValueByDefault() {
        softly.assertThat(personalDetailsPage.getEeoRaceAndEthnicitySelect().waitUntilEnabled().getValue().isEmpty());
    }

    @Then ("I check that error message with text $errorText appears under EEO Race and Ethnicity field")
    public void checkErrorMessageAppearsUnderEEORaceAndEthnicityFieldWithNoValue(String errorTextMessage) {
        softly.assertThat(personalDetailsPage.getErrorMessageUnderEEORaceAndEthnicityFieldWithNoValue().waitUntilVisible().getText()).as("Wrong message is shown")
                .contains(errorTextMessage);
    }
}
