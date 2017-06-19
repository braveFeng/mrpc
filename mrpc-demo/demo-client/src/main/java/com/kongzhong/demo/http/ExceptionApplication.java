package com.kongzhong.demo.http;

import com.kongzhong.mrpc.client.RpcClient;
import com.kongzhong.mrpc.demo.exception.BizException;
import com.kongzhong.mrpc.demo.model.Person;
import com.kongzhong.mrpc.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Http异常测试
 *
 * @author biezhi
 *         2017/4/19
 */
public class ExceptionApplication {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        rpcClient.setTransport("http");

        UserService userService = rpcClient.getProxyBean(UserService.class);
        try {
            userService.testNormalExp();
            userService.testBizExp(2333);
        } catch (Exception e) {
            if (e instanceof BizException) {
                BizException bizException = (BizException) e;
                System.out.println(bizException.getCode() + ":" + bizException.getMsg());
            } else {
                e.printStackTrace();
            }
        }
        rpcClient.stop();
    }
}
