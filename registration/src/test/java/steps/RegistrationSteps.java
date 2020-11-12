package steps;

import data.StaticDataKeys;
import io.qameta.allure.Step;
import model.Field;
import org.assertj.core.api.SoftAssertions;
import pages.HomePage;
import pages.PageBase;
import pages.PaymentPage;
import pages.RegistrationPage;
import types.PageType;
import types.TestDataFile;
import types.TestDataType;
import utils.PageConfigProvider;
import utils.TestDataProvider;

import java.util.List;
import java.util.Map;

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
                .isEqualToIgnoringCase(expectedText);
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

    @Step("Fill all the Fields of the given page with Specified Test Data")
    public void fillAllFieldsOfThePage(TestDataType testDataType, TestDataFile dataFileType){
        List<Field> fieldsOfThePage = getRegistrationPageFields();
        Map<String, String> testData = testDataProvider.getTestData(testDataType, dataFileType);
        for (Field field:fieldsOfThePage) {
            fillTheGivenField(field, testData.get(field.getFieldName()), field.getFieldName());
        }
    }


    @Step("Fill all fields excluding given list of fields , with specific test data")
    public void fillAllFieldsExcludingGivenFields(List<String> fieldNames, TestDataType testDataType, TestDataFile dataFileType) {
        List<Field> fieldsOfThePage = getRegistrationPageFields();
        Map<String, String> testData = testDataProvider.getTestData(testDataType, dataFileType);
        for (Field field:fieldsOfThePage) {
            if (!fieldNames.contains(field.getFieldName())){
                fillTheGivenField(field, testData.get(field.getFieldName()), field.getFieldName());
            }
        }

    }

    @Step("Fill only given list of fields , with specified test data")
    public void fillOnlyGivenListOfFields(List<String> fieldNames, TestDataType testDataType, TestDataFile dataFileType) {
         List<Field> fieldsOfThePage = getRegistrationPageFields();
         Map<String, String> testData = testDataProvider.getTestData(testDataType, dataFileType);
        for (Field field:fieldsOfThePage) {
            if (fieldNames.contains(field.getFieldName())){
                fillTheGivenField(field, testData.get(field.getFieldName()), field.getFieldName());
            }
        }

    }

    @Step("Click Registration Button and Verify Reaching Payment Page")
    public void clickRegistrationAndVerifyReachingPaymentPage() {
        if (registrationPage == null) {
            throw  new RuntimeException("Not navigated to Registration page !, Please navigate to Registration page before click Register");
        }
        registrationPage.clickRegistrationButton();
        PaymentPage paymentPage = new PaymentPage();
        softAssertions.assertThat(paymentPage.getPPageTitleText()).as("Verify Payment Page Title Text")
                .isEqualTo("Almost done!");
    }

    @Step("Click Registration Button and Verify Validation Field Message for the field : {fieldName}")
    public void clickRegistrationButtonAndVerifyValidationMessageForGivenField(String fieldName, StaticDataKeys messageKey) {
        if (registrationPage == null) {
            throw  new RuntimeException("Not navigated to Registration page !, Please navigate to Registration page before click Register");
        }

        registrationPage.clickRegistrationButton();

        Field fieldToVerified = getRegistrationPageFields()
                .stream().filter(f->f.getFieldName().equals(fieldName)).findFirst()
                .orElseThrow(()-> new RuntimeException("Given Field Not Found in Registration Page"));

        String expectedText = testDataProvider.getTestDataByKey(TestDataType.STATIC, TestDataFile.VALID,
                messageKey.name());

        softAssertions.assertThat(registrationPage.getValidationMessage(fieldToVerified))
                .as("Verify Validation Message").isEqualTo(expectedText);
    }

    @Step("Fill the field : {fieldName} with the value : {value} ")
    private void fillTheGivenField(Field field, String value,  String fieldName) {
        if (registrationPage == null) {
            throw  new RuntimeException("Not navigated to Registration page !, Please navigate to Registration page before filling fields");
        }

        registrationPage.fillGivenField(field, value);
    }

    private List<Field> getRegistrationPageFields() {
        PageConfigProvider pageConfigProvider = new PageConfigProvider();
        return pageConfigProvider.getPageConfigurationData(PageType.REGISTRATION);
    }


    public void closeBrowser() {
        currentPage.closeBrowser();
    }
}
