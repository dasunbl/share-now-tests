package com.dasun.carspecs.data;

import com.dasun.carspecs.types.TestDataKeys;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusData {

    private TestDataKeys dataKey;
    private String vehicleId;
    private String value;
}
