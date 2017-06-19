package com.kongzhong.demo.http;

import com.kongzhong.mrpc.client.RpcClient;
import com.kongzhong.mrpc.demo.model.Person;
import com.kongzhong.mrpc.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Http其他测试
 *
 * @author biezhi
 *         2017/4/19
 */
public class HttpOtherApplication {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        rpcClient.setTransport("http");

        UserService userService = rpcClient.getProxyBean(UserService.class);

        int sum = userService.add(10, 20);

        System.out.println("add => " + sum);

        Person person = new Person();
        person.setName("王爵nice");

        Person person1 = userService.savePerson("hihihi", 99);
        System.out.println("save result = " + person1);

        Map<String, Integer> map = new HashMap<>();
        map.put("Hello", 22);
        Map<String, Integer> rmap = userService.toMap(map);
        System.out.println("toMap => " + rmap);

        rpcClient.stop();
    }
}
