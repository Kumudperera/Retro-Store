package com.teamx.retroStore.dao.master.user.jdbc;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.dao.BaseJDBCDao;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.user.UserDTO;
import com.teamx.retroStore.dto.master.user.UserSearchRQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserJDBCDao extends BaseJDBCDao {

    public Page<UserDTO> getPagedUsers(UserSearchRQ searchRQ) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                                                                                              \n");
        SQL.append("  tu.ID,                                                                                       \n");
        SQL.append("  CONCAT(tu.FIRST_NAME,' ', tu.LAST_NAME) AS DISPLAY_NAME,                                          \n");
        SQL.append("  tu.USERNAME,                                                                                      \n");
        SQL.append("  tu.USER_TYPE,                                                                                     \n");
        SQL.append("  tu.EMAIL,                                                                                         \n");
        SQL.append("  tu.STATUS                                                                                         \n");
        SQL.append("FROM                                                                                                \n");
        SQL.append("  t_user tu                                                                                         \n");
        SQL.append("WHERE                                                                                               \n");
        SQL.append("   tu.ID IS NOT NULL                                                                           \n");

        if (StringUtils.isNotEmpty(searchRQ.getUsername())) {
            SQL.append("  AND tu.USERNAME LIKE '%" + searchRQ.getUsername() + "%'                                       \n");
        }

        if (StringUtils.isNotEmpty(searchRQ.getFirstName())) {
            SQL.append("  AND tu.FIRST_NAME LIKE '%" + searchRQ.getFirstName() + "%'                                    \n");
        }
        if (StringUtils.isNotEmpty(searchRQ.getLastName())) {
            SQL.append("  AND tu.LAST_NAME LIKE '%" + searchRQ.getLastName() + "%'                                      \n");
        }
        if (StringUtils.isNotEmpty(searchRQ.getDisplayName())) {
            SQL.append("  AND CONCAT(tu.FIRST_NAME, ' ', tu.LAST_NAME) LIKE '%" + searchRQ.getDisplayName() + "%'       \n");
        }
        if (StringUtils.isNotEmpty(searchRQ.getEmail())) {
            SQL.append("  AND tu.EMAIL LIKE '%" + searchRQ.getEmail() + "%'                                             \n");
        }
        if (searchRQ.getStatus() != null) {
            SQL.append(" AND tu.STATUS = :status                                                                        \n");
            params.put("status", searchRQ.getStatus().toString());
        }
        SQL.append("ORDER BY tu.ID DESC");

        return getPagedData(SQL.toString(), params, (rs, rowNum) -> {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(rs.getInt("ID"));
            userDTO.setDisplayName(rs.getString("DISPLAY_NAME"));
            userDTO.setUsername(rs.getString("USERNAME"));
            userDTO.setEmail(rs.getString("EMAIL"));
            userDTO.setStatus(AppsConstants.Status.resolveStatus(rs.getString("STATUS")));

            return userDTO;
        }, searchRQ);
    }

    public Integer getUserIDFromEmail(String email) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                                                                                              \n");
        SQL.append("  tu.ID                                                                                        \n");
        SQL.append("FROM                                                                                                \n");
        SQL.append("  t_user tu                                                                                         \n");
        SQL.append("WHERE                                                                                               \n");
        SQL.append("   tu.ID IS NOT NULL                                                                           \n");
        SQL.append("   AND tu.STATUS = 'ACT'                                                                            \n");
        SQL.append("   AND LOWER(tu.EMAIL) = :email                                                                     \n");
        params.put("email", email.trim().toLowerCase());

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<Integer>() {
            @Nullable
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    if (StringUtils.isNotEmpty(rs.getString("ID"))) {
                        return rs.getInt("ID");
                    }
                }
                return null;
            }
        });
    }


    public List<String> getUsersNotificationTokens(List<Integer> userIDs) {
        final List<String> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("userIDs", userIDs);

        StringBuffer SQL = new StringBuffer();
        SQL.append("SELECT DISTINCT tun.NOTIFICATION_TOKEN                           \n");
        SQL.append("FROM t_user_notification tun                                     \n");
        SQL.append("         INNER JOIN t_user tu ON tun.USER_ID = tu.ID        \n");
        SQL.append("WHERE tu.ID in (:userIDs)                                   \n");
        SQL.append("  AND tun.NOTIFICATION_TOKEN IS NOT NULL                         \n");

        namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {

                    result.add(rs.getString("NOTIFICATION_TOKEN"));
                }

                return result;
            }
        });

        return result;
    }

}
