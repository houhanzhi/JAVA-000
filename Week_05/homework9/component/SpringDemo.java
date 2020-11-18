package com.string.test.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 来自猩猩的攻城狮hhz
 * @date 2020-11-15 22:17
 */
public class SpringDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RoleConfig.class);
        Role bean = context.getBean(Role.class);
        System.out.println(bean.toString());
    }
}
