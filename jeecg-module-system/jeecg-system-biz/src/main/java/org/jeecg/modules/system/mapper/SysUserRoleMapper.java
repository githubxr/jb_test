package org.jeecg.modules.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 通过用户账号查询角色集合
     * @param username 用户账号名称
     * @return List<String>
     */
	@Select("select role_code from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleByUserName(@Param("username") String username);

	/**
     * 通过用户账号查询角色Id集合
     * @param username 用户账号名称
     * @return List<String>
     */
	@Select("select id from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleIdByUserName(@Param("username") String username);

	////
	/**
	 * 获取拥有指定职务且不拥有指定角色的用户
	 * @postName 指定职务名
	 * @isLike 职务名是否模糊匹配
	 * @roleNames 要排除角色名
	*/
	public List<SysUser> getUserByPostNameAndWithoutRole(String postName, boolean isLike, String[] roleNames);

	/**
	 * 通过职务名获取用户
	 * @postName
	 * @isLike 是否使用模糊匹配
	 * @return 用户列表
	 */
	public List<SysUser> getUserByPostName(String postName);

	/**
	 *筛选出没有指定权限的用户
	 * @postName
	 * @userIds
	 * @return 符合条件的用户id列表
	 * */
	public List<String> getUserWithoutPost(String postName, List<SysUser> users);

}
