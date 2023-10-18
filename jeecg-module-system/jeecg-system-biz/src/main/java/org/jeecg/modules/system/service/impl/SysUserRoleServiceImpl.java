package org.jeecg.modules.system.service.impl;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.mapper.SysRoleMapper;
import org.jeecg.modules.system.mapper.SysUserMapper;
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
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public boolean syncRoleByPost(String postName, String[] roleNames){
        //批量 根据角色名查角色id
        LambdaQueryWrapper<SysRole> roleLqw = new LambdaQueryWrapper<>();
        roleLqw.select(SysRole::getId).in(SysRole::getRoleName, roleNames);
        List<SysRole> roles = sysRoleMapper.selectList(roleLqw);
        List<String> roleIds = roles.stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
        //批量 根据角色id查用户
        LambdaQueryWrapper<SysUserRole> userRoleLqw = new LambdaQueryWrapper<>();
        userRoleLqw.select(SysUserRole::getUserId)
                .in(SysUserRole::getRoleId, roleIds)
                .groupBy(SysUserRole::getUserId);
        List<SysUserRole> userWithRole = sysUserRoleMapper.selectList(userRoleLqw);
        List<String> userWithRoleIds = userWithRole.stream()
                .map(SysUserRole::getUserId)
                .collect(Collectors.toList());
        //根据职位名查用户
        List<SysUser> users = sysUserMapper.getUserByPostName(postName,true);
        List<String> userIds = users.stream()
                .map(SysUser::getId)
                .collect(Collectors.toList());
        //删除交集
        userIds.removeAll(userWithRoleIds);

        //添加用户角色
        List<SysUserRole> userRoleList = new ArrayList<>();
        for(String roleId:roleIds){
            for(String userId:userIds){
                SysUserRole sysUserRole = new SysUserRole(userId,roleId);
                userRoleList.add(sysUserRole);
            }
        }
        return this.saveBatch(userRoleList);
    }
    /*
    public boolean syncRoleByPost(String postName, String[] roleNames){
        //批量 根据角色名查角色id
        LambdaQueryWrapper<SysRole> roleLqw = new LambdaQueryWrapper<>();
        roleLqw.select(SysRole::getId).in(SysRole::getRoleName, roleNames);
        List<SysRole> roles = sysRoleMapper.selectList(roleLqw);
        List<String> roleIds = roles.stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
        //批量 根据角色id查用户
        LambdaQueryWrapper<SysUserRole> userRoleLqw = new LambdaQueryWrapper<>();
        userRoleLqw.select(SysUserRole::getUserId)
                .in(SysUserRole::getRoleId, roleIds)
                .groupBy(SysUserRole::getUserId);
        List<SysUserRole> userWithRole = sysUserRoleMapper.selectList(userRoleLqw);
        List<String> userWithRoleIds = userWithRole.stream()
                .map(SysUserRole::getUserId)
                .collect(Collectors.toList());
        //根据职位名查用户
        List<SysUser> users = sysUserMapper.getUserByPostName(postName,true);
        List<String> userIds = users.stream()
                .map(SysUser::getId)
                .collect(Collectors.toList());
        //删除交集
        userIds.removeAll(userWithRoleIds);

        //添加用户角色
        List<SysUserRole> userRoleList = new ArrayList<>();
        for(String roleId:roleIds){
            for(String userId:userIds){
                SysUserRole sysUserRole = new SysUserRole(userId,roleId);
                userRoleList.add(sysUserRole);
            }
        }
        return this.saveBatch(userRoleList);
    }
    */
    public static void main(String[] args) {
        System.out.println("第1句");
        System.out.println("第2句");
        System.out.println("第3句");
    }
}
