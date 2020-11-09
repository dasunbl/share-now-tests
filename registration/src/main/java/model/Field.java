package model;

import lombok.Getter;
import lombok.Setter;
import types.FieldType;

@Getter
@Setter
public class Field {

    private String fieldName;
    private String identifier;
    private FieldType fieldType;

}
