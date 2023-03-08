package com.steps;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Map;

public class ListOfClinicsSteps {

    private static final String GetClinicURL = "https://qa-challenge-api.scratchpay.com/api/clinics";

    @Step
    public void getClinicDetails(String term) {
        SerenityRest.given()
                .headers(
                        "Authorization",
                        "Bearer " + Serenity.sessionVariableCalled("bearer"))
                .log().all()
                .queryParam("term", term)
                .when().get(GetClinicURL);
    }

    @Step
    public void getClinicDetails() {
        SerenityRest.given()
                .headers(
                        "Authorization",
                        "Bearer " + Serenity.sessionVariableCalled("bearer"))
                .log().all()
                .when().get(GetClinicURL);
    }

    @Step
    public void getClinicDetailsWithoutAuth(String term) {
        SerenityRest.given()
                .log().all()
                .queryParam("term", term)
                .when().get(GetClinicURL);
    }

    @Step
    public List<Map<String, Object>> validateListOfClinicsResponse() {
        return SerenityRest.then().log().all().extract().body().jsonPath().get("data");
    }
}
