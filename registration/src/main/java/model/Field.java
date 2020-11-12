package model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Getter;
import lombok.Setter;
import types.FieldType;

@Getter
@Setter
public class Field {

    private String fieldName;
    private String identifier;
    private FieldType fieldType;
    private String xpathIdentifier;

}
