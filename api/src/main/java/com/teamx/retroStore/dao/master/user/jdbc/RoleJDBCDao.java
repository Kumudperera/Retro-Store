package com.teamx.retroStore.dao.master.user.jdbc;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dao.BaseJDBCDao;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.user.PrivilegeDTO;
import com.teamx.retroStore.dto.master.user.RoleDTO;
import com.teamx.retroStore.dto.master.user.RoleSearchRQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleJDBCDao extends BaseJDBCDao {

    public Page<RoleDTO> getPagedRoles(RoleSearchRQ searchRQ) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT r.ID,                                                                \n");
        SQL.append("  r.NAME,                                                                   \n");
        SQL.append("  r.DESCRIPTION,                                                                 \n");
        SQL.append("  r.STATUS                                                                       \n");
        SQL.append("FROM t_role r                                                                    \n");
        SQL.append("WHERE r.ID IS NOT NULL                                                      \n");
        if (StringUtils.isNotEmpty(searchRQ.getName())) {
            SQL.append("AND upper(r.NAME) LIKE '%" + searchRQ.getName().toUpperCase() + "%' \n");
        }
        if (searchRQ.getStatus() != null) {
            SQL.append("AND r.STATUS = :status                                                       \n");
            params.put("status", searchRQ.getStatus().toString());
        }
        SQL.append("ORDER BY r.NAME");

        return getPagedData(SQL.toString(), params, new RowMapper<RoleDTO>() {
            @Nullable
            @Override
            public RoleDTO mapRow(ResultSet rs, int i) throws SQLException {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setID(rs.getInt("ID"));
                roleDTO.setName(rs.getString("NAME"));
                roleDTO.setDescription(rs.getString("DESCRIPTION"));
                roleDTO.setStatus(AppsConstants.Status.resolveStatus(rs.getString("status")));

                return roleDTO;
            }
        }, searchRQ);
    }

    public List<RoleDTO> getRoles(AppsConstants.Status status) {
        final List<RoleDTO> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT r.ID,                                                                       \n");
        SQL.append("  r.NAME,                                                                          \n");
        SQL.append("  r.DESCRIPTION,                                                                        \n");
        SQL.append("  r.STATUS                                                                              \n");
        SQL.append("FROM t_role r                                                                           \n");
        SQL.append("WHERE r.ID IS NOT NULL                                                             \n");

        if (status != null) {
            SQL.append("AND r.status = :status                                                              \n");
            params.put("status", status.toString());
        }
        SQL.append("ORDER BY r.NAME");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<RoleDTO>>() {

            @Nullable
            @Override
            public List<RoleDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setID(rs.getInt("ID"));
                    roleDTO.setName(rs.getString("NAME"));
                    roleDTO.setDescription(rs.getString("DESCRIPTION"));
                    roleDTO.setStatus(AppsConstants.Status.resolveStatus(rs.getString("status")));

                    result.add(roleDTO);
                }
                return result;
            }
        });
    }

    public List<PrivilegeDTO> getPrivileges(AppsConstants.Status status) {
        final List<PrivilegeDTO> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT p.ID,                                                              \n");
        SQL.append("  p.NAME,                                                                 \n");
        SQL.append("  p.CODE,                                                                 \n");
        SQL.append("  p.BEHAVIOUR_DESCRIPTION,                                                \n");
        SQL.append("  pc.CATEGORY_DESCRIPTION,                                                \n");
        SQL.append("  pc.MODULE,                                                              \n");
        SQL.append("  p.STATUS                                                                \n");
        SQL.append("FROM t_privilege p                                                        \n");
        SQL.append("  INNER JOIN t_privilege_category pc ON                                   \n");
        SQL.append("  p.privilege_category_id = pc.ID                                         \n");
        SQL.append("WHERE p.ID IS NOT NULL                                                    \n");
        if (status != null) {
            SQL.append("AND p.STATUS = :status                                                \n");
            params.put("status", status.toString());
        }
        SQL.append("ORDER BY p.CODE                                                           \n");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<PrivilegeDTO>>() {

            @Nullable
            @Override
            public List<PrivilegeDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    PrivilegeDTO privilegeDTO = new PrivilegeDTO();
                    privilegeDTO.setID(rs.getInt("ID"));
                    privilegeDTO.setCode(rs.getString("code"));
                    privilegeDTO.setName(rs.getString("name"));
                    privilegeDTO.setDescription(rs.getString("behaviour_description"));
                    privilegeDTO.setCategory(rs.getString("category_description"));
                    privilegeDTO.setModule(DomainConstants.PrivilegeModule.valueOf(rs.getString("module")));
                    privilegeDTO.setStatus(AppsConstants.Status.resolveStatus(rs.getString("status")));

                    result.add(privilegeDTO);
                }
                return result;
            }
        });
    }

    public List<RoleDTO> getSystemRoles() {
        final List<RoleDTO> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                                                                                              \n");
        SQL.append("  tr.ID ,                                                                                      \n");
        SQL.append("  tr.NAME ,                                                                                    \n");
        SQL.append("  tr.DESCRIPTION ,                                                                                  \n");
        SQL.append("  tr.STATUS                                                                                         \n");
        SQL.append("FROM                                                                                                \n");
        SQL.append("  t_role tr                                                                                         \n");
        SQL.append("WHERE                                                                                               \n");
        SQL.append("  tr.ID IS NOT NULL                                                                            \n");
        SQL.append("  AND tr.STATUS = 'ACT'                                                                             \n");
        SQL.append("  ORDER BY tr.NAME                                                                             \n");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<RoleDTO>>() {

            @Nullable
            @Override
            public List<RoleDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setID(rs.getInt("ID"));
                    roleDTO.setName(rs.getString("NAME"));
                    roleDTO.setDescription(rs.getString("DESCRIPTION"));
                    roleDTO.setStatus(AppsConstants.Status.resolveStatus(rs.getString("STATUS")));

                    result.add(roleDTO);
                }
                return result;
            }
        });
    }

    public List<Integer> getRolesWithSameRoleName(String roleName, Integer companyID) {
        final List<Integer> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                                                                                     \n");
        SQL.append("  tr.ID                                                                                    \n");
        SQL.append("FROM                                                                                       \n");
        SQL.append("  t_role tr                                                                                \n");
        SQL.append("WHERE                                                                                      \n");
        SQL.append("  tr.NAME = BINARY :roleName                                                               \n");
        params.put("roleName", roleName);


        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<Integer>>() {

            @Nullable
            @Override
            public List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    result.add(rs.getInt("ID"));
                }
                return result;
            }
        });
    }
}
