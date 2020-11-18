package com.string.test.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过注解装配bean---->@Component
 */

@Component(value = "role")
@Data
public class Role {

    @Value("1")
    private String id;

    @Value("张三")
    private String role_name;
}
