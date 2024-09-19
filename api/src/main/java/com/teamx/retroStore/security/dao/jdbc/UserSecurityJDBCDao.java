package com.teamx.retroStore.security.dao.jdbc;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.dao.BaseJDBCDao;
import com.teamx.retroStore.exception.impl.AppsCommonErrorCode;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.security.dto.AuthDTO;
import com.teamx.retroStore.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserSecurityJDBCDao extends BaseJDBCDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityJDBCDao.class);

    public AuthDTO getUserByUsername(String userName) throws AppsException {
        LOG.info("START: User retrieval for user {} ", userName);

        final AuthDTO user = new AuthDTO();
        QueryBuilder SQL = new QueryBuilder();

        SQL.append("SELECT                                                                                 \n");
        SQL.append("  tu.ID,                                                                               \n");
        SQL.append("  tu.USERNAME,                                                                         \n");
        SQL.append("  concat(tu.FIRST_NAME, ' ', tu.LAST_NAME) as DISPLAY_NAME,                            \n");
        SQL.append("  tu.PASSWORD,                                                                         \n");
        SQL.append("  tu.EMAIL                                                                             \n");
        SQL.append("FROM                                                                                   \n");
        SQL.append("  t_user tu                                                                            \n");
        SQL.append("INNER JOIN t_user_role tur ON                                                          \n");
        SQL.append("  tur.USER_ID = tu.ID                                                                  \n");
        SQL.append("LEFT JOIN t_role_privilege trp ON                                                      \n");
        SQL.append("  tur.ROLE_ID = trp.ROLE_ID                                                            \n");
        SQL.append("LEFT JOIN t_privilege tp ON                                                            \n");
        SQL.append("  tp.ID = trp.PRIVILEGE_ID                                                             \n");
        SQL.append("WHERE                                                                                  \n");
        SQL.appendNotNullMandatory("tu.STATUS = :status ", AppsConstants.Status.ACT.toString());
        SQL.appendNotNullMandatory("AND tu.USERNAME = BINARY :username ", userName);


        try {
            namedParameterJdbcTemplate.queryForObject(SQL.toString(), SQL.getParameterMap(), new RowMapper<AuthDTO>() {
                @Nullable
                @Override
                public AuthDTO mapRow(ResultSet rs, int i) throws SQLException {
                    user.setID(rs.getInt("ID"));
                    user.setUsername(rs.getString("USERNAME"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setEmail(rs.getString("EMAIL"));

                    return user;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            LOG.warn("User with user details `{}` does not exist", userName);
            throw new AppsException(AppsCommonErrorCode.APPS_EMPTY_RESULT, AppsConstants.ResponseStatus.SUCCESS);
        }

        LOG.info("END: User retrieval for {} completed as {}", userName, user);
        return user;
    }


    public void getAssignedTerminal(Integer ID) {
    }
}
