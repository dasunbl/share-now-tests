package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.FieldNameKeys;
import org.apache.commons.lang3.RandomStringUtils;
import types.TestDataFile;
import types.TestDataType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestDataProvider {

    private String dataFilePath  = System.getProperty("user.dir") + "/src/main/resources/testdata/{data_type}-{data-file}.json";

    /**
     * Get Test Data
     * @param testDataType test data type
     * @param file file type
     * @return Test as Map
     */
    public Map<String, String> getTestData(TestDataType testDataType, TestDataFile file) {
        String path =  dataFilePath.replace("{data_type}", testDataType.name().toLowerCase())
                .replace("{data-file}", file.name().toLowerCase());

        Map<String, String> data = new HashMap<>();
        try {
            data = new ObjectMapper().readValue(new File(path), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when reading test of data type: " + testDataType);
        }

        if (testDataType.equals(TestDataType.USER) && file.equals(TestDataFile.VALID)) {
            setDynamicEmailAndPhoneNumber(data);
        }

        return  data ;
    }

    /**
     * Get specific test data by given Key
     * @param testDataType Test Data Type
     * @param file File Type
     * @param key Key
     * @return Specific Test Data
     */
    public String getTestDataByKey(TestDataType testDataType, TestDataFile file, String key) {
        String value = getTestData(testDataType, file).get(key);
        if (value == null){
            throw new RuntimeException("Given Key : " + key + " Not found in test data set");
        }

        return value;
    }

    private  void  setDynamicEmailAndPhoneNumber(Map<String, String> testData){
       String email = "dtestcoding+{random}@outlook.com";
       String randomEmailString =   RandomStringUtils.random(6,true,false);
       testData.put(FieldNameKeys.EMAIL,  email.replace("{random}", randomEmailString));

       String mobilePhone = "303994{random}";
       String randomMobileString =   RandomStringUtils.random(5,false,true);
        testData.put(FieldNameKeys.PHONE,  mobilePhone.replace("{random}", randomMobileString));

    }
}
