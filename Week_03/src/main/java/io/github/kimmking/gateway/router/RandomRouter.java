package io.github.kimmking.gateway.router;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 负载均衡随机算法
 */
public class RandomRouter {

    //    1.定义map, key-ip,value-weight
    static Map<String,Integer> ipMap=new HashMap<>();
    static {
        ipMap.put("http://localhost:8088/api/hello",1);
        ipMap.put("http://localhost:8801",2);
        ipMap.put("http://localhost:8802",3);
    }
    public String random() {
        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        Set<String> ipSet=ipServerMap.keySet();

        //定义一个list放所有server
        ArrayList<String> ipArrayList=new ArrayList<String>();
        ipArrayList.addAll(ipSet);

        //循环随机数
        Random random=new Random();
        //随机数在list数量中取（1-list.size）
        int pos=random.nextInt(ipArrayList.size());
        String serverNameReturn= ipArrayList.get(pos);
        return  serverNameReturn;
    }

    public static void main(String[] args) {
        RandomRouter testRandom=new RandomRouter();
        for (int i =0;i<10;i++){
            String server=testRandom.random();
            System.out.println(server);
        }
    }
}
