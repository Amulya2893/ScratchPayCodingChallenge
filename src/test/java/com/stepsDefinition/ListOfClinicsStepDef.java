package com.stepsDefinition;

import com.Utils.ValidateResponse;
import com.steps.ListOfClinicsSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ListOfClinicsStepDef {

    @Steps
    ListOfClinicsSteps listOfClinicsSteps;
    @Steps
    private ValidateResponse validateResponse;

    @When("user searches clinics with <term>")
    public void searchClinic(@Named("term") String term) {
        listOfClinicsSteps.getClinicDetails(term);
    }

    @When("user searches clinics without term")
    public void searchClinic() {
        listOfClinicsSteps.getClinicDetails();
    }

    @When("unauthenticated user searches clinics with <term>")
    public void searchClinicWithoutAuth(@Named("term") String term) {
        listOfClinicsSteps.getClinicDetailsWithoutAuth(term);
    }

    @Then("the user is able to retrieve all the clinics for the term")
    public void verifySearchClinic() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(200);
        assertThat(validateResponse.getOK()).isEqualTo(true);
        List<Map<String, Object>> data = listOfClinicsSteps.validateListOfClinicsResponse();
        ;
        for (Map entry : data) {
            assertThat(!entry.isEmpty()).isTrue();
            assertThat(entry.containsKey("id")).isTrue();
            assertThat(entry.containsKey("displayName")).isTrue();
        }
    }

    @Then("the user is unable to retrieve clinic details")
    public void verifySearchClinicWithoutTerm() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(422);
        assertThat(validateResponse.getOK()).isEqualTo(false);
    }

    @Then("unauthenticated user is unable to retrieve clinic details")
    public void verifySearchClinicFoeUnauthenticatedUser() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(401);
        assertThat(validateResponse.getOK()).isEqualTo(false);
    }
}
