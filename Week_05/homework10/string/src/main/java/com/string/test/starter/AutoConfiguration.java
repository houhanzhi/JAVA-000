package com.string.test.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(value = Student.class)
@EnableConfigurationProperties(StudentProperties.class)
public class  AutoConfiguration {

    @Resource
    private StudentProperties studentProperties;

    @Bean
    @ConditionalOnMissingBean(Student.class)
    @ConditionalOnProperty(prefix = "student",value = "enabled",havingValue="true")
    public Student student(){
        Student student=new Student(studentProperties.getId(), studentProperties.getName());
        return student;
    }

}
