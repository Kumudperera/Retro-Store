package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.dto.common.PagedParamDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleSearchRQ extends PagedParamDTO {

    private String name;

    private AppsConstants.Status status;
}
