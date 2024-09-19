package com.teamx.retroStore.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.common.PagedParamDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseJDBCDao {

    private static Logger LOG = LoggerFactory.getLogger(BaseJDBCDao.class);

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected <T extends Serializable> Page<T> getPagedData(String dataQuery, Map<String, Object> paramsMap,
                                                            RowMapper<T> rowMapper, PagedParamDTO gridPramDTO) {

        Integer pageIndex = gridPramDTO.getPage() - 1;
        Integer rows = gridPramDTO.getRows();
        Integer start = pageIndex * rows;

        Long totalNoOfRecs = namedParameterJdbcTemplate.queryForObject(this.getCountQuery(dataQuery), paramsMap,
                Long.class);

        paramsMap.put("start", start);
        paramsMap.put("noOfRows", rows);

        Collection<T> resultsList = namedParameterJdbcTemplate.query(getPagedQuery(dataQuery), paramsMap,
                rowMapper);

        return new Page<T>(totalNoOfRecs, start, resultsList.size(), rows, resultsList);
    }

    protected <T extends Serializable> Page<T> getPagedData(String dataQuery, MapSqlParameterSource paramsMap,
                                                            RowMapper<T> rowMapper, PagedParamDTO gridPramDTO) {

        Integer pageIndex = gridPramDTO.getPage() - 1;
        Integer rows = gridPramDTO.getRows();
        Integer start = pageIndex * rows;

        Long totalNoOfRecs = namedParameterJdbcTemplate.queryForObject(this.getCountQuery(dataQuery), paramsMap,
                Long.class);

        paramsMap.addValue("start", start);
        paramsMap.addValue("noOfRows", rows);

        Collection<T> resultsList = namedParameterJdbcTemplate.query(getPagedQuery(dataQuery), paramsMap,
                rowMapper);

        return new Page<T>(totalNoOfRecs, start, resultsList.size(), rows, resultsList);
    }

    private String getPagedQuery(String query) {
        StringBuilder pagedQuery = new StringBuilder(query);
        pagedQuery.append(" LIMIT :start,:noOfRows  ");

        return pagedQuery.toString();
    }

    private String getCountQuery(String query) {
        StringBuilder countQuery = new StringBuilder();
        countQuery.append("SELECT count(*) FROM ( ");
        countQuery.append(query);
        countQuery.append(" ) count");

        return countQuery.toString();
    }

    private Map<String, Object> convertJsonToMap(String json, List<String> keyList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(json);
        Map<String, Object> map = new HashMap<>();
        for (String key : keyList) {
            map.put(key, jsonNodeRoot.get(key).asText());
        }
        return map;
    }

    protected Map<String, Object> extractJsonAsMap(String json, List<String> keyList) {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            dataMap = convertJsonToMap(json, keyList);
        } catch (JsonProcessingException e) {
            LOG.warn("Error: converting json to map", e);
        }
        return dataMap;
    }
}
