package pages;

import actions.FieldActionProvider;
import model.Field;
import org.openqa.selenium.By;

public class RegistrationPage extends PageBase {

    public void fillGivenField(Field field, String value){
        FieldActionProvider actionProvider = new FieldActionProvider();
        actionProvider.getFieldAction(field).fill(getDriver(), value);
    }


}
