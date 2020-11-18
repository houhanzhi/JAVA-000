package com.string.test.xml;

import lombok.Data;

/**
 * 奶茶店
 */

@Data
public class MakeJuice {

    private String name;

    private Source source;

    public String make(){
        String res = "这是由"+name+"奶茶店制作的"+source.getSize()+source.getFruit()+source.getSugar()+"的饮品";
        return res;
    }
}
