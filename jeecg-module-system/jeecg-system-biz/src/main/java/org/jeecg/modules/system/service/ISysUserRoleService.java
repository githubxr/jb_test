package org.jeecg.modules.system.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    /**
     *通过职务名同步角色
     * 为拥有指定职务的用户添加指定的角色
     * @postName 职务名
     * @roles 要添加的角色列表
     * return 成功与否
     * */
    public boolean syncRoleByPost(String postName, String[] roles);
}
