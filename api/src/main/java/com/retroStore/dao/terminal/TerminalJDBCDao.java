package com.retroStore.dao.terminal;

import com.retroStore.dao.BaseJDBCDao;
import com.retroStore.dto.common.Page;
import com.retroStore.dto.master.terminal.SelectedFolderPageDTO;
import com.retroStore.dto.master.terminal.SelectedFolderSearchRQ;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TerminalJDBCDao extends BaseJDBCDao {

    public Page<SelectedFolderPageDTO> getPagedSelectedFolders(SelectedFolderSearchRQ searchRQ) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT                           \n");

        return getPagedData(SQL.toString(), params, new RowMapper<SelectedFolderPageDTO>() {
            @Nullable
            @Override
            public SelectedFolderPageDTO mapRow(ResultSet rs, int i) throws SQLException {
                SelectedFolderPageDTO selectedFolderPageDTO = new SelectedFolderPageDTO();

                return selectedFolderPageDTO;
            }
        }, searchRQ);
    }
}
