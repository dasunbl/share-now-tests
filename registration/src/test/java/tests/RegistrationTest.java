package tests;

import data.FieldNameKeys;
import data.StaticDataKeys;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import steps.RegistrationSteps;
import types.TestDataFile;
import types.TestDataType;

import java.util.List;


@Epic("Shopping Cart Tests")
@Feature("Basic Cart Operations Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationTest {

    private SoftAssertions softAssertions;
    private RegistrationSteps registrationSteps;

    @BeforeEach
    public void beforeMethod() {
        softAssertions =  new SoftAssertions();
        registrationSteps = new RegistrationSteps(softAssertions);

    }

    @AfterEach
    public void shutDown(){
        softAssertions.assertAll();
        registrationSteps.closeBrowser();
    }

    @Test
    @Tag("ui")
    @DisplayName("Verify Performing a Valid Registration(Up to Payment Page)")
    public void verifyPerformingValidRegistration(){
        registrationSteps.openHomePageAcceptCookiesAndVerifyRegisterButtonText();
        registrationSteps.navigateToRegistrationPage();
        registrationSteps.fillAllFieldsOfThePage(TestDataType.USER, TestDataFile.VALID);
        registrationSteps.clickRegistrationAndVerifyReachingPaymentPage();
    }

    @Test
    @Tag("ui")
    @DisplayName("Verify mandatory field validation for a single field")
    @Description("Verification of Mandatory fields ideally need to be performed like this by , filling all other fields" +
            "and clicking Registration button, test can be parameterized to run the same for all the fields")
    public void verifyMandatoryFieldValidation(){
        registrationSteps.openHomePageAcceptCookiesAndVerifyRegisterButtonText();
        registrationSteps.navigateToRegistrationPage();
        registrationSteps.fillAllFieldsExcludingGivenFields(List.of(FieldNameKeys.EMAIL), TestDataType.USER, TestDataFile.VALID);
        registrationSteps.clickRegistrationButtonAndVerifyValidationMessageForGivenField(FieldNameKeys.EMAIL, StaticDataKeys.REQUIRED_FIELD_TEXT);
    }

    @Test
    @Tag("ui")
    @DisplayName("Verify invalid value validation of a single field : Email")
    @Description("This test can also be parameterized for all the fields , but slight enhancement of Test Data Structure would be needed")
    public void verifyInvalidValueValidation(){
        registrationSteps.openHomePageAcceptCookiesAndVerifyRegisterButtonText();
        registrationSteps.navigateToRegistrationPage();
        registrationSteps.fillOnlyGivenListOfFields(List.of(FieldNameKeys.EMAIL), TestDataType.USER, TestDataFile.INVALID);
        registrationSteps.clickRegistrationButtonAndVerifyValidationMessageForGivenField(FieldNameKeys.EMAIL, StaticDataKeys.EMAIL_VALIDATION);
    }
}
