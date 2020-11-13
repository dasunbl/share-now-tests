package com.dasun.carspecs.utils;

import com.dasun.carspecs.data.StatusData;
import com.dasun.carspecs.types.TestDataKeys;
import com.dasun.carspecs.types.TestDataType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.emory.mathcs.backport.java.util.Arrays;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private String dataFilePath  = System.getProperty("user.dir") + "/src/main/resources/testdata/{data_type}-data.json";

    /**
     * Get Test Data
     * @param testDataType Test Data Type
     * @return List of Status Data
     */
    public List<StatusData> getTestData(TestDataType testDataType) {
        String path =  dataFilePath.replace("{data_type}", testDataType.name().toLowerCase());

        List statusDataList = new ArrayList<>();
        try {
            statusDataList = Arrays.asList(new ObjectMapper().readValue(new File(path),StatusData[].class));
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when reading test of data type: " + testDataType);
        }

        return  statusDataList;
    }

    public StatusData getTestDataByKey(TestDataType testDataType, TestDataKeys dataKey){
      return   getTestData(testDataType).stream()
                .filter(f->f.getDataKey().equals(dataKey))
                .findFirst().orElseThrow(()-> new RuntimeException("No Data found for given key "+ dataKey));
    }
}
