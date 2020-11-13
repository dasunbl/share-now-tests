package com.dasun.registration.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.emory.mathcs.backport.java.util.Arrays;
import com.dasun.registration.model.Field;
import com.dasun.registration.types.PageType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PageConfigProvider {

    private String dataFilePath  = System.getProperty("user.dir") + "/src/main/resources/pageconfigs/{page_type}-page.json";



    public List<Field> getPageConfigurationData(PageType pageType) {
        String path =  dataFilePath.replace("{page_type}", pageType.name().toLowerCase());

        List<Field> pageConfigList;
        try {
            pageConfigList = Arrays.asList(new ObjectMapper().readValue(new File(path), Field[].class));
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when reading Page configurations data : " + pageType);
        }

        return  pageConfigList;
    }
}
