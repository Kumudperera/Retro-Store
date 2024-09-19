package com.teamx.retroStore.dto.master.user;

public class UserLoadOptionDTO {

    private boolean loadRoleIDs;

    public boolean isLoadRoleIDs() {
        return this.loadRoleIDs;
    }

    public void loadRoleIDs() {
        this.loadRoleIDs = true;
    }

}
