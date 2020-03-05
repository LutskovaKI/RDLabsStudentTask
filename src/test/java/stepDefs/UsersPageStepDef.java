package stepDefs;

import grids.UsersGrid;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.Keys;
import pageComponents.FilterUsersModalWindow;
import steps.DefaultStepsData;
import steps.UsersSteps;

import java.util.List;
import java.util.Map;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

public class UsersPageStepDef extends DefaultStepsData {

    @Steps
    private UsersSteps usersSteps;

    @When("filter users by Employee Name $userName")
    public void filterUsersByEmployeeName(String employeeName) {
        usersSteps.filterUsersByEmployeeName(employeeName);
    }

    @Then("record is shown with following parameters:$table")
    public void checkResultOfFiltering(ExamplesTable examplesTable) {
        Map<String, String> row = examplesTable.getRow(0);
        List<UsersGrid> allItems = usersSteps.getUsersGrid();
        softly.assertThat(allItems).as("Wrong search result size is shown").hasSize(1);
        softly.assertThat(allItems.get(0).getUserName()).as("Wrong [Username] is shown").isEqualTo(row.get("Username"));
        softly.assertThat(allItems.get(0).getUserRole()).as("Wrong [User Role(s)] is shown").isEqualTo(row.get("User Role(s)"));
        softly.assertThat(allItems.get(0).getEmployeeName()).as("Wrong [Employee Name] is shown").isEqualTo(row.get("Employee Name"));
        softly.assertThat(allItems.get(0).getStatus()).as("Wrong [Status] is shown").isEqualTo(row.get("Status"));
        softly.assertThat(allItems.get(0).getRegions()).as("Region field is not empty").isEqualTo(row.get("Region"));
    }

    @Then("I open filter users window")
    @Alias("I click on the Filter users button again")
    public void openFilterUsersWindow() {
        usersSteps.openFilterWindow();
    }

    @When("I click on the Search button in Filter Users window")
    public void clickOnTheSearchButtonInFilterUsersWindow() {
        usersSteps.clickOnTheSearchButton();
    }

    @Then("I filter user by Status with option Disabled")
    @Alias("I select any value from Status select")
    public void filterUsersByStatusDisabled(){
        usersPage.getUserStatusField().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        }

    @Then("I filter user by Admin Role with option Global Admin")
    @Alias("I select any value from Admin Role select")
    public void filterUsersByAdminRoleGlobalAdmin(){
        usersPage.getUserAdminRoleField().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
    }

    @Then("I check that employee with name $usersName $condition in the search result")
    public void checkEmployeeIsShown(String usersName, String condition) {
        if (condition.startsWith("NOT")) {
            softly.assertThat(usersSteps.checkEmployeePresentAfterFiltering(usersName)).isTrue();
        } else {
            softly.assertThat(usersSteps.checkEmployeePresentAfterFiltering(usersName)).isFalse();
        }
    }

    @Then("I check that previously entered values saved in Status and Admin Role selects")
    public void checkThatPreviouslyValuesIsSaved() {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        softly.assertThat(filterUsersModalWindow.getStatus().getValue()).isEqualTo("Disabled");
        softly.assertThat(filterUsersModalWindow.getAdminRole().getValue()).isEqualTo("Global Admin");
    }
}