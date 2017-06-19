package com.kongzhong.demo.http;

import com.kongzhong.mrpc.client.RpcClient;
import com.kongzhong.mrpc.demo.service.UserService;

/**
 * Http数组测试
 *
 * @author biezhi
 *         2017/4/19
 */
public class HttpArrayApplication {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        rpcClient.setTransport("http");

        UserService userService = rpcClient.getProxyBean(UserService.class);

        userService.testArray(new String[]{"a", "b", "c"});

        rpcClient.stop();
    }
}
