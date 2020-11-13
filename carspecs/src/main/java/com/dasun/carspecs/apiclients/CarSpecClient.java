package com.dasun.carspecs.apiclients;

import com.dasun.carspecs.types.Environment;
import com.dasun.carspecs.utils.ConfigurationProvider;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CarSpecClient {

    private String apiHost;
    private RequestSpecification requestSpecification;
    private static final String frontLeftDoorPath = "/vehicles/{vehicleId}/resources/doorstatusfrontleft";

    public CarSpecClient() {
        setAPIHost();
        RestAssured.reset();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());

        RequestSpecBuilder builder =  new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(apiHost);
        requestSpecification = builder.build();
    }

    public Response getFrontLeftDoorResources(String vehicleId) {
      return   given()
                .headers(
                        "Authorization",
                        "Bearer " + "4c4c444c-v123-4123-s123-4c4c444c4c44",
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .spec(requestSpecification)
                .pathParam("vehicleId", vehicleId)
                .get(frontLeftDoorPath);
    }



    private void setAPIHost(){
        Environment env ;
        try {
            env = Environment.valueOf(System.getProperty("env"));
        } catch (IllegalArgumentException | NullPointerException e) {
            env = Environment.QA;
        }

        switch (env){
            case PROD:
                apiHost =  ConfigurationProvider.getConfigurationByKey("host.prod");
                break;
            default:
                apiHost =  ConfigurationProvider.getConfigurationByKey("host.qa");
        }
    }
}
