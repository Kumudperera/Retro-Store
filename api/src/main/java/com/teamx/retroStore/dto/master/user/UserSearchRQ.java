package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.dto.common.PagedParamDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class UserSearchRQ extends PagedParamDTO {

    private Integer userID;

    private String firstName;

    private String lastName;

    private String displayName;

    private String username;

    private String email;

    private AppsConstants.Status status;

    private List<Integer> userIDs;

    public List<Integer> getUserIDs() {
        if (userIDs == null) {
            userIDs = new ArrayList<>();
        }
        return userIDs;
    }

}
