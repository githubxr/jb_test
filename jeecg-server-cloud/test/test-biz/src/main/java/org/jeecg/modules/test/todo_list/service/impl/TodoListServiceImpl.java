package org.jeecg.modules.test.todo_list.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.bouncycastle.util.Arrays;
import org.jeecg.modules.test.todo_list.entity.TodoList;
import org.jeecg.modules.test.todo_list.mapper.TodoListMapper;
import org.jeecg.modules.test.todo_list.service.ITodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description: 待办列表
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Service
public class TodoListServiceImpl extends ServiceImpl<TodoListMapper, TodoList> implements ITodoListService {
    @Autowired
    private TodoListMapper todoListMapper;
    public boolean isGroupExist(){
        QueryWrapper<TodoList> wrapper = new QueryWrapper<>();
        wrapper.select("count(id)")
                .in("title", "first", "secondary")
                .groupBy("title");
        ArrayList<Object> bothCount = (ArrayList<Object>) todoListMapper.selectObjs(wrapper);
        return bothCount.size() == 2;
    }

    /*
    public boolean isGroupExist(){
        int bothExist = todoListMapper.bothExist("first","secondary");
        return bothExist == 2;
    }
    * */
}
