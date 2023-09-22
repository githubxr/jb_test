package com.alibaba.nacos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test920 {
    //test2 new line 1
    //test2 new line 2
    public static void main(String[] args) {
        int i = 0;
        boolean bool = true;
        bool = bool & --i>100;
        System.out.println("i:"+i);
    }

    static void test1(){
        String[] array1 = {"a", "b", "c", "d"};
        String[] array2 = {"c", "d", "e", "f"};

        List<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        List<String> list2 = new ArrayList<>(Arrays.asList("c", "d", "e", "f"));
        List<String> list3 = new ArrayList<>(list1);
        list1.removeAll(list2);
        //list1.retainAll(list2);
        //list3.removeAll(list1);
        System.out.println("list1:"+list1);
        System.out.println("list2:"+list2);
        System.out.println("list3:"+list3);
    }
}
