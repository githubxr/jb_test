package org.jeecg.modules.test.todo_list.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jeecg.common.api.dto.system.SysUserRoleDTO;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.SysDepartModel;
import org.jeecg.modules.test.todo_list.entity.TodoList;
import org.jeecg.modules.test.todo_list.service.ITodoListService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 待办列表
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Api(tags="待办列表")
@RestController
@RequestMapping("/todo_list/todoList")
@Slf4j
public class TodoListController extends JeecgController<TodoList, ITodoListService> {
	@Autowired
	private ITodoListService todoListService;

	@Autowired
	private ISysBaseAPI iSysBaseAPI;
	
	/**
	 * 分页列表查询
	 *
	 * @param todoList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "待办列表-分页列表查询")
	@ApiOperation(value="待办列表-分页列表查询", notes="待办列表-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "todo_list/TodoListList")
	public Result<IPage<TodoList>> queryPageList(TodoList todoList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TodoList> queryWrapper = QueryGenerator.initQueryWrapper(todoList, req.getParameterMap());
		Page<TodoList> page = new Page<TodoList>(pageNo, pageSize);
		IPage<TodoList> pageList = todoListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param todoList
	 * @return
	 */
	@AutoLog(value = "待办列表-添加")
	@ApiOperation(value="待办列表-添加", notes="待办列表-添加")
	//@RequiresPermissions("todo_list:todo_list:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TodoList todoList) {
		if(todoList.getStartTime().after(todoList.getEndTime())){
			return Result.error("结束日期不能小于开始日期!");
		}

		todoListService.save(todoList);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param todoList
	 * @return
	 */
	@AutoLog(value = "待办列表-编辑")
	@ApiOperation(value="待办列表-编辑", notes="待办列表-编辑")
	//@RequiresPermissions("todo_list:todo_list:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TodoList todoList) {
		todoListService.updateById(todoList);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "待办列表-通过id删除")
	@ApiOperation(value="待办列表-通过id删除", notes="待办列表-通过id删除")
	//@RequiresPermissions("todo_list:todo_list:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		todoListService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "待办列表-批量删除")
	@ApiOperation(value="待办列表-批量删除", notes="待办列表-批量删除")
	//@RequiresPermissions("todo_list:todo_list:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.todoListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "待办列表-通过id查询")
	@ApiOperation(value="待办列表-通过id查询", notes="待办列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TodoList> queryById(@RequestParam(name="id",required=true) String id) {
		TodoList todoList = todoListService.getById(id);
		if(todoList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(todoList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param todoList
    */
    //@RequiresPermissions("todo_list:todo_list:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TodoList todoList) {
        return super.exportXls(request, todoList, TodoList.class, "待办列表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("todo_list:todo_list:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TodoList.class);
    }

	 /**
	  * 获取系统时间
	  */
	 @AutoLog(value = "待办列表-获取系统时间")
	 @ApiOperation(value="待办列表-获取系统时间", notes="待办列表-获取系统时间")
	 @GetMapping(value = "/sysTime")
	 public Result<DateTime> sysTime(){
		 return Result.ok(new DateTime());
	 }

	 @AutoLog(value = "待办列表-双列测试")
	 @ApiOperation(value="待办列表-双列测试", notes="待办列表-双列测试")
	 @GetMapping(value="/sameExists")
	 public Result<String> sameExists(){
		 boolean isGroupExist = todoListService.isGroupExist();
		 return isGroupExist? Result.ok():Result.error("isGroupExist:" + isGroupExist);
	 }

	 @AutoLog(value = "待办列表-测试feign")
	 @ApiOperation(value="待办列表-测试feign", notes="待办列表-测试feign")
	 @GetMapping(value="/feignTest")
	 public Result<Object> feignTest(){
		 List<SysUserRoleDTO> sysUserRoleDTOs = new ArrayList<>();

		 List<String> ids1 = new ArrayList<>(),
				 		ids2 = new ArrayList<>();

		 ids1.add("1691738976949977089");
		 ids1.add("1696362025947004929");
		 ids2.add("3d464b4ea0d2491aab8a7bde74c57e95");

		 sysUserRoleDTOs.add(new SysUserRoleDTO("财务出纳",ids1));
		 sysUserRoleDTOs.add(new SysUserRoleDTO("人力资源部",ids2));

		 Result<Boolean> feignState =  iSysBaseAPI.addSysUserBatch(sysUserRoleDTOs);
		 return Result.OK(feignState);
	 }
 }
