package org.jeecg.modules.system.service.impl;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.mapper.SysRoleMapper;
import org.jeecg.modules.system.mapper.SysUserRoleMapper;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecg.modules.system.service.ISysUserRoleService;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public boolean syncRoleByPost(String postName, String[] roles) {
        boolean isSucc = true;
        //获取拥有指定职务的用户
        List<SysUser> users = sysUserRoleMapper.getUserByPostName(postName);
        for(String roleName:roles){
            //剔除已拥有角色的用户
            List<String> usersWithoutRole = sysUserRoleMapper.getUserWithoutPost(roleName, users);

            List<SysUserRole> userRoleList = new ArrayList<>();
            SysRole role = sysRoleMapper.getOneByName(roleName);
            for(String userId:usersWithoutRole){
                SysUserRole sysUserRole = new SysUserRole(userId,role.getId());
                userRoleList.add(sysUserRole);
            }
            isSucc = isSucc && this.saveBatch(userRoleList);
        }
        return isSucc;
    }

    public static void main(String[] args) {
        System.out.println("第1句");
        System.out.println("第2句");
        System.out.println("第3句");
    }
}
