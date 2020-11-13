package com.dasun.carspecs.tests;

import com.dasun.carspecs.apiclients.CarSpecClient;
import com.dasun.carspecs.data.StatusData;
import com.dasun.carspecs.model.RootResponse;
import com.dasun.carspecs.types.TestDataKeys;
import com.dasun.carspecs.types.TestDataType;
import com.dasun.carspecs.utils.TestDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

@Epic("Car Spec API tests")
@Feature("Front Left Door API Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FrontLeftDoorResourceApiTests {

    private SoftAssertions softAssertions;
    private CarSpecClient carSpecClient;
    private TestDataProvider dataProvider;

    @BeforeAll
    public void setUp() {
        carSpecClient =  new CarSpecClient();
        dataProvider =  new TestDataProvider();
    }

    @BeforeEach
    public void beforeMethod() {
        softAssertions =  new SoftAssertions();

    }

    @AfterEach
    public void shutDown(){
        softAssertions.assertAll();
    }

    @DisplayName("Send Valid Front Left Door Resource Request")
    @Tag("api")
    @Test
    public  void verifySendingValidRequest(){
        StatusData data = dataProvider.getTestDataByKey(TestDataType.STATUS, TestDataKeys.VALID_VEHICLE);
        Response response = carSpecClient.getFrontLeftDoorResources(data.getVehicleId());
        softAssertions.assertThat(response.statusCode()).as("Verify Status Code").isEqualTo(HttpStatus.SC_OK);
        RootResponse frontLeftDoorResponse = response.as(RootResponse.class);
        softAssertions.assertThat(frontLeftDoorResponse.getDoorstatusfrontleft().getValue()).as("Verify Value")
                .isEqualTo(data.getValue());
    }

    @DisplayName("Send Front Left Door status request for invalid vehicle")
    @Tag("api")
    @Test
    public  void verifySendingRequestWithInvalidData(){
        StatusData data = dataProvider.getTestDataByKey(TestDataType.STATUS, TestDataKeys.INVALID_VEHICLE);
        Response response = carSpecClient.getFrontLeftDoorResources(data.getVehicleId());
        softAssertions.assertThat(response.statusCode()).as("Verify Status Code").isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}
