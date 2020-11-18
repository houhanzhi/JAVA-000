package com.string.test.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 来自猩猩的攻城狮hhz
 * @date 2020-11-18 13:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private int id;

    private String name;

    public void hello(){
        System.out.println("hello .............");
    }
}
