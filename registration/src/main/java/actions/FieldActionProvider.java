package actions;

import model.Field;

public class FieldActionProvider {


    public CommonFieldAction getFieldAction(Field field){
         CommonFieldAction fieldAction;
         switch (field.getFieldType()) {
             case SELECT:
                 fieldAction = new SelectFieldAction(field);
                 break;
             case CHECKBOX:
                 fieldAction = new CheckBoxFieldAction(field);
                 break;
             default:
                 fieldAction = new TextBoxFieldAction(field);
            }
       return fieldAction;
    }
}
