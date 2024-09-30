package com.teamx.retroStore.dao.master.storagePackage;

import com.teamx.retroStore.dao.BaseJDBCDao;
import com.teamx.retroStore.dto.master.storagePackage.SearchRQ;
import com.teamx.retroStore.dto.master.storagePackage.StoragePackageDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCDao extends BaseJDBCDao {
    public List<StoragePackageDTO> getStoragePackages(SearchRQ searchRQ) {
        final List<StoragePackageDTO> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                                             \n");
        SQL.append("tsp.ID,                                            \n");
        SQL.append("tsp.NAME,                                          \n");
        SQL.append("tsp.DESCRIPTION,                                   \n");
        SQL.append("tsp.STORAGE,                                       \n");
        SQL.append("tsp.STORAGE_UNIT,                                  \n");
        SQL.append("tsp.PERIOD,                                        \n");
        SQL.append("tsp.PERIOD_PRICE,                                  \n");
        SQL.append("tsp.STATUS                                        \n");
        SQL.append("FROM                                               \n");
        SQL.append("t_storage_package tsp                              \n");

        namedParameterJdbcTemplate.query(SQL.toString(), params, rs -> {
            while (rs.next()) {
                StoragePackageDTO storagePackageDTO = new StoragePackageDTO(rs);

                result.add(storagePackageDTO);
            }

            return result;
        });

        return result;
    }
}
