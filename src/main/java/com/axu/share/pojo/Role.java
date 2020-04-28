package com.axu.share.pojo;

import org.springframework.stereotype.Component;

@Component
public class Role {

    private String userUuid;//用户uuid

    private String roleId;//角色id

    private String roleName;//角色id

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
