package org.jeecg.module.test.todo_list;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.JeecgTestCloudApplication;
import org.jeecg.modules.test.todo_list.entity.TodoList;
import org.jeecg.modules.test.todo_list.mapper.TodoListMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgTestCloudApplication.class)
@RunWith(SpringRunner.class)
public class SimpleTest {
    @Autowired
    private TodoListMapper todoListMapper;

    @Test
    public void test1(){
        String name = "me";
        QueryWrapper<TodoList> wrapper = new QueryWrapper<>();
        String vir = String.format("sum(handler='%s' and 1=%s) as isOwner",name,1);
        wrapper.select("*");
        System.out.println("\n wrapper.getSqlSelect()\n" + wrapper.getSqlSelect());
        wrapper.select(wrapper.getSqlSelect(), vir);
        wrapper.orderBy( true, false,"isOwner");
        List<TodoList> list = todoListMapper.selectList(wrapper);
        System.out.println(list.size());
    }
}
