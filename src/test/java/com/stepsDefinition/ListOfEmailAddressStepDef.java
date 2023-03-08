package com.stepsDefinition;

import com.Utils.ValidateResponse;
import com.steps.AuthenticationSteps;
import com.steps.ListOfEmailAddressSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ListOfEmailAddressStepDef {
    @Steps
    private AuthenticationSteps authenticationSteps;
    @Steps
    private ValidateResponse validateResponse;

    @Steps
    private ListOfEmailAddressSteps listOfEmailAddressSteps;

    @Given("user is authenticated")
    public void checkUserAuthentication() {
        assertThat(authenticationSteps.getAuthToken()).isNotNull();
    }

    @When("user tries to get the list of email address for practice id $practiceId")
    public void getListOfEmailAddress(int practiceId) {
        listOfEmailAddressSteps.getEmailAddress(practiceId);
    }


    @When("unauthenticated user tries to get the list of email address for practice id $practiceId")
    public void getListOfEmailAddressWithoutAuth(int practiceId) {
        listOfEmailAddressSteps.getEmailAddressWithoutAuth(practiceId);
    }

    @When("user tries to get the list of email address without practiceId")
    public void getListOfEmailAddressWithoutPracticeId() {
        listOfEmailAddressSteps.getEmailAddressWithoutPracticeId();
    }

    @Then("user should be able to get the list of emails")
    public void validateGetListOfEMailAddressResponse() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(200);
        assertThat(validateResponse.getOK()).isEqualTo(true);
        List<Map<String, Object>> users = listOfEmailAddressSteps.validateGetEmailAddressResponse();
        for (Map user : users) {
            if (user.containsKey("email")) {
                assertThat(user.get("email")).isNotNull();
            }
        }
    }

    @Then("user should be get a valid error message")
    public void validateGetListOfEmailAddressErrors() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(400);
        assertThat(validateResponse.getOK()).isEqualTo(false);
        assertThat(listOfEmailAddressSteps.validateGetEmailAddressError()).isEqualTo("Error: Invalid practiceId");
    }

    @Then("user should be be prevented to get the list")
    public void validateGetListOfEmailAddressResponseWithoutAuth() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(401);
        assertThat(validateResponse.getOK()).isEqualTo(false);
    }

    @Then("user should be prevented from getting the list with permission denied error message")
    public void validateGetListOfEmailAddressErrorResponse() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(400);
        assertThat(validateResponse.getOK()).isEqualTo(false);
        assertThat(listOfEmailAddressSteps.validateGetEmailAddressErrorMessage()).isEqualTo("An error happened");
        assertThat(listOfEmailAddressSteps.validateGetEmailAddressError()).isEqualTo("Error: User does not have permissions");
    }


}
