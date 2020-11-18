package com.example.demo.controller;

import com.string.test.starter.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 来自猩猩的攻城狮hhz
 * @date 2020-11-18 13:36
 */

@RestController
public class TestController {

    @Autowired
    private Student student;

    @GetMapping("student")
    public void teste(){
        System.out.println(student);
    }
}
