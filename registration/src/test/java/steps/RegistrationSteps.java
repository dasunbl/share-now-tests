package steps;

import data.StaticDataKeys;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import pages.HomePage;
import pages.PageBase;
import pages.RegistrationPage;
import types.TestDataFile;
import types.TestDataType;
import utils.TestDataProvider;

public class RegistrationSteps {

    private HomePage homePage;
    private RegistrationPage registrationPage;
    private SoftAssertions softAssertions;
    private TestDataProvider testDataProvider;
    private PageBase currentPage;

    public RegistrationSteps(SoftAssertions softAssert) {
        this.softAssertions = softAssert;
        testDataProvider =  new TestDataProvider();

    }

    @Step("Open Application, accept cookies and Verify Join Button Text")
    public void openHomePageAcceptCookiesAndVerifyRegisterButtonText() {
        homePage  = new HomePage();
        homePage.openHomePage();
        homePage.getCookiePopUp().acceptCookies();

        String expectedText = testDataProvider.getTestDataByKey(TestDataType.STATIC, TestDataFile.VALID,
                StaticDataKeys.JOIN_BUTTON_TEXT.name());

        softAssertions.assertThat(homePage.getHeader().getRegisterButtonText())
                .as("Verify Join Button Text")
                .isEqualTo(expectedText);
        currentPage = homePage;
    }

    @Step("Navigate To Registration Page")
    public void navigateToRegistrationPage(){
        if (homePage == null) {
            throw  new RuntimeException("Application Not Opened !, Please open application before trying to navigate");
        }

        homePage.getHeader().clickRegisterButton();
        registrationPage = new RegistrationPage();
        currentPage = registrationPage;
    }


    public void closeBrowser() {
        currentPage.closeBrowser();
    }
}
