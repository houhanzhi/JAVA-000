package com.string.test.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 来自猩猩的攻城狮hhz
 * @date 2020-11-15 21:42
 */
public class SpringDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        Role role1 = (Role) context.getBean("role1");
        System.out.println(role1.toString());


        MakeJuice juice = (MakeJuice) context.getBean("juice");
        String make = juice.make();
        System.out.println(make);


        ListParam list = (ListParam) context.getBean("list1");
        list.getList().forEach(System.out::println);

        UserList userList = (UserList) context.getBean("userList");
        userList.getRoleList().forEach(System.out::println);
    }
}
