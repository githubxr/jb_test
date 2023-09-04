
package org.jeecg.common.api.dto.system;

import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleDTO {
    private String roleName;
    private List<String> userIds;

    public SysUserRoleDTO(){}
    public SysUserRoleDTO(String roleName, List<String> userIds) {
        this.roleName=roleName;
        this.userIds=userIds;
    }

}
