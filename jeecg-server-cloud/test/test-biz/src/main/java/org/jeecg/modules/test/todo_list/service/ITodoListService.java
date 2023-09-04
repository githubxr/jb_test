package org.jeecg.modules.test.todo_list.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.test.todo_list.entity.TodoList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.test.todo_list.mapper.TodoListMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 待办列表
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface ITodoListService extends IService<TodoList> {
    public boolean isGroupExist();
}
