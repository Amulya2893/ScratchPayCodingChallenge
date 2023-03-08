package com.steps;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class AuthenticationSteps {

    private static final String getAuthURL = "https://qa-challenge-api.scratchpay.com/api/auth";

    @Step
    public void getEmailAndPassword() {
        Serenity.setSessionVariable("email").to("gianna@hightable.test");
        Serenity.setSessionVariable("password").to("thedantonio1");
    }

    @Step
    public void getInvalidEmail() {
        Serenity.setSessionVariable("invalidEmail").to("gianna@highble.test");
        Serenity.setSessionVariable("password").to("thedantonio1");
    }

    @Step
    public void getInvalidPassword() {
        Serenity.setSessionVariable("email").to("gianna@highble.test");
        Serenity.setSessionVariable("invaliPassword").to("tntonio");
    }

    @Step
    public void requestLogin() {
        SerenityRest.given()
                .log().all()
                .queryParam("email", Serenity.sessionVariableCalled("email"))
                .queryParam("password", Serenity.sessionVariableCalled("password"))
                .when().get(getAuthURL);
    }

    @Step
    public void requestLoginWithoutCredentials() {
        SerenityRest.given()
                .log().all()
                .when().get(getAuthURL);
    }

    public String getAuthToken() {
        String bearer = SerenityRest.then().log().all().extract().body().jsonPath().get("data.session.token");
        Serenity.setSessionVariable("bearer").to(bearer);
        return bearer;
    }

    @Step
    public boolean getAuthenticationStatus() {
        return SerenityRest.then().extract().body().jsonPath().get("data.session.loggedIn");
    }

    @Step
    public Integer getAuthenticationErrorCode() {
        return SerenityRest.then().extract().body().jsonPath().get("data.code");
    }

    public String getAuthenticationErrorMessage() {
        return SerenityRest.then().extract().body().jsonPath().get("data.message");
    }
}
