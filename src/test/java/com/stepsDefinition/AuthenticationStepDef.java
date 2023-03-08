package com.stepsDefinition;

import com.Utils.ValidateResponse;
import com.steps.AuthenticationSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.assertj.core.api.Assertions.assertThat;


public class AuthenticationStepDef {

    @Steps
    private AuthenticationSteps authenticationStep;
    @Steps
    private ValidateResponse validateResponse;

    @Given("valid user details")
    public void getUserDetails() {
        authenticationStep.getEmailAndPassword();
    }

    @Given("invalid user email details")
    public void getInvalidEmailDetails() {
        authenticationStep.getInvalidEmail();
    }

    @Given("invalid user passcode details")
    public void getInvalidPasscodeDetails() {
        authenticationStep.getInvalidPassword();
    }


    @When("user tries to login")
    public void tryLogin() {
        authenticationStep.requestLogin();
    }

    @When("a user tries to login without credentials")
    public void tryLoginWithoutCredentials() {
        authenticationStep.requestLoginWithoutCredentials();
    }

    @Then("user should be able to login")
    public void validateAuthResponse() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(200);
        assertThat(validateResponse.getOK()).isEqualTo(true);
        assertThat(authenticationStep.getAuthenticationStatus()).isTrue();
        assertThat(authenticationStep.getAuthToken()).isNotNull();
    }

    @Then("user should not be able to login")
    public void validateAuthErrorResponse() {
        assertThat(validateResponse.getStatusCode()).isEqualTo(400);
        assertThat(validateResponse.getOK()).isEqualTo(false);
        assertThat(authenticationStep.getAuthenticationErrorCode()).isEqualTo(20203);
        assertThat(authenticationStep.getAuthenticationErrorMessage()).isEqualTo("Invalid login credentials");
    }
}
