package com.teamx.retroStore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ReportUtil {

    /*
    * Usage
    * String json = "json string"
        List<String> keyList = new ArrayList<>();
        keyList.add("personReporting1");
        keyList.add("categoryName1");
        String foundObjectValue = ReportUtil.convertJson(json, keyList);
        *
    * */

    public static String convertJson(String json, List<String> keyList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(json);

        for (String key : keyList) {
            if (jsonNodeRoot == null) {
                return null;
            }
            jsonNodeRoot = jsonNodeRoot.get(key);
        }
        return jsonNodeRoot != null ? jsonNodeRoot.asText() : null;
    }

}
