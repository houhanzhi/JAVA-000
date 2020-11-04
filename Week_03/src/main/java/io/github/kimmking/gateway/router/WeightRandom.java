package io.github.kimmking.gateway.router;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加权随机策略
 */
public class WeightRandom {
    //    1.定义map, key-ip,value-weight
    static Map<String, Integer> ipMap = new HashMap<>();

    static {
        ipMap.put("http://localhost:8088/api/hello",4);
        ipMap.put("http://localhost:8801",2);
        ipMap.put("http://localhost:8802",1);


    }

    public String random() {
        Map<String, Integer> ipServerMap = new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        Set<String> ipSet = ipServerMap.keySet();
        Iterator<String> ipIterator = ipSet.iterator();

        //定义一个list放所有server
        ArrayList<String> ipArrayList = new ArrayList<String>();

        //循环set，根据set中的可以去得知map中的value，给list中添加对应数字的server数量
        while (ipIterator.hasNext()) {
            String serverName = ipIterator.next();
            Integer weight = ipServerMap.get(serverName);
            for (int i = 0; i < weight; i++) {
                ipArrayList.add(serverName);
            }
        }

        //循环随机数
        Random random = new Random();
        //随机数在list数量中取（1-list.size）
        int pos = random.nextInt(ipArrayList.size());
        String serverNameReturn = ipArrayList.get(pos);
        return serverNameReturn;
    }

    public static void main(String[] args) {
        WeightRandom testWeightRandom = new WeightRandom();
        for (int i = 0; i < 10; i++) {
            String server = testWeightRandom.random();
            System.out.println(server);
        }


    }
}
