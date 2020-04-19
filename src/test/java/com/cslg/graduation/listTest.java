package com.cslg.graduation;

import com.cslg.graduation.service.ActiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述 测试类
 *
 * @author ruozhuliufeng
 * @version 1.0
 * @date 2020/4/12 23:49
 */
@SpringBootTest
public class listTest {

    @Autowired
    private ActiveService activeService;

    @Test
    public void testListDict(){
        List list = new ArrayList();
        list.add(26);
        list.add(39);
        list.add(5);
        list.add(40);
        list.add(39);
        list.add(25);
        System.out.println(list);
        List newList = Collections.singletonList(list.stream().distinct().collect(Collectors.toList()));
        System.out.println("java8新特性stream去重:"+newList);
    }

    @Test
    public void testList(){
        boolean flag = activeService.findNewsUser(5);
        System.out.println(flag);
    }
}
