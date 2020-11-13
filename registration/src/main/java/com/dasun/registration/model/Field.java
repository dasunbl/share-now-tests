package com.dasun.registration.model;

import lombok.Getter;
import lombok.Setter;
import com.dasun.registration.types.FieldType;

@Getter
@Setter
public class Field {

    private String fieldName;
    private String identifier;
    private FieldType fieldType;
    private String xpathIdentifier;

}
