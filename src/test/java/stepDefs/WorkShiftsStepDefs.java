package stepDefs;

import grids.UsersGrid;
import grids.WorkShiftGrid;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import steps.DefaultStepsData;
import steps.WorkShiftsSteps;

import java.util.List;
import java.util.Map;

public class WorkShiftsStepDefs extends DefaultStepsData {

    @Steps
    private WorkShiftsSteps workShiftsSteps;

    @When("I click on Add Work Shift button")
    public void clickOnAddWorkShiftButton() {
        workShiftsSteps.clickOnAddWorkShiftButton();
    }

    @Then("I check that rows with values $firstValue, $secondValue in WorkShift column are shown by default")
    public void checkValuesInDefaultWorkShift(String firstValue, String secondValue) {
        List<WorkShiftGrid> allItems = workShiftsSteps.getWorkShiftGrid();
        softly.assertThat(allItems.get(0).getWorkShift()).as("Wrong [First Value] is shown").isEqualTo(firstValue);
        softly.assertThat(allItems.get(1).getWorkShift()).as("Wrong [Second Value] is shown").isEqualTo(secondValue);
    }

    @Then ("I click on Save button in Add Work Shift window")
    public void clickOnSaveAddWorkShiftButton()  {
        workShiftPage.clickOnSaveAddWorkShiftButton();
    }

    @Then ("I check that $errorMessage error message is shown under Work Shift field")
    public void errorMessageIsShownUnderWorkShiftField(String errorMessage) {
        softly.assertThat(workShiftPage.getDefaultErrorMessageText()).as("Text in error message field is different").contains(errorMessage);
    }

//    @Then ("Using time picker I set 10:50 value into From filed")
//
//    @Then ("Using time picker I set 18:20 value into To filed")
//
//    @Then ("I check that 7.50 value calculated in Hours Per Day field")
//
//    @Then ("Using time picker I set 8:05 value into From filed")
//
//    @Then ("Using time picker I set 20:25 value into To filed")
//
//    @Then ("I check that 12.33 value calculated in Hours Per Day field")

}
