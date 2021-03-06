package com.kongzhong.demo.http;

import com.kongzhong.mrpc.client.RpcSpringClient;
import com.kongzhong.mrpc.demo.exception.BizException;
import com.kongzhong.mrpc.demo.service.UserService;

/**
 * @author biezhi
 *         2017/4/19
 */
public class ExceptionApplication {

    public static void main(String[] args) {
        RpcSpringClient rpcClient = new RpcSpringClient();
        rpcClient.setTransport("http");

        UserService userService = rpcClient.getProxyReferer(UserService.class);

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
