package com.Utils;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ValidateResponse {
    @Step
    public int getStatusCode() {
        return SerenityRest.then().log().all().extract().statusCode();
    }

    @Step
    public boolean getOK() {
        return SerenityRest.then().log().all().extract().body().jsonPath().get("ok");
    }

}

