package com.steps;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Map;
public class ListOfEmailAddressSteps {

    private static Response response;
    private static final String GetClinicURL = "https://qa-challenge-api.scratchpay.com/api/clinics/";

    @Step
    public void getEmailAddress(int practiceId) {
        SerenityRest.given()
                .headers(
                        "Authorization",
                        "Bearer " + Serenity.sessionVariableCalled("bearer"))
                .pathParam("practiceId", practiceId)
                .pathParam("email", "emails")
                .log().all()
                .when().get(GetClinicURL + "{practiceId}/{email}");
    }

    @Step
    public void getEmailAddressWithoutAuth(int practiceId) {
        SerenityRest.given()
                .pathParam("practiceId", practiceId)
                .pathParam("email", "emails")
                .log().all()
                .when().get(GetClinicURL + "{practiceId}/{email}");
    }

    @Step
    public void getEmailAddressWithoutPracticeId() {
        SerenityRest.given()
                .headers(
                        "Authorization",
                        "Bearer " + Serenity.sessionVariableCalled("bearer"))
                .pathParam("email", "emails")
                .log().all()
                .when().get(GetClinicURL + "{email}");
    }

    @Step
    public List<Map<String, Object>> validateGetEmailAddressResponse() {
        return SerenityRest.then().log().all().extract().body().jsonPath().get("data.users");
    }

    @Step
    public String validateGetEmailAddressErrorMessage() {
        return SerenityRest.then().log().all().extract().body().jsonPath().get("data.message");
    }

    @Step
    public String validateGetEmailAddressError() {
        return SerenityRest.then().log().all().extract().body().jsonPath().get("data.error");
    }
}
