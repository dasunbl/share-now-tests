package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import steps.RegistrationSteps;

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
        registrationSteps.closeBrowser();
    }

    @Test
    @Tag("sanity")
    public void performValidRegistration(){
        registrationSteps.openHomePageAcceptCookiesAndVerifyRegisterButtonText();
        registrationSteps.navigateToRegistrationPage();
    }
}
