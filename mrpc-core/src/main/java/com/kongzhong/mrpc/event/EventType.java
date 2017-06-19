package com.kongzhong.mrpc.event;

/**
 * 事件类型
 *
 * @author biezhi
 *         2017/6/19
 */
public enum EventType {

    SEVER_EXECUTE_FINISHED("服务端执行成功"),
    SEVER_EXECUTE_TIMEOUT("服务端执行超时"),
    SEVER_EXECUTE_ERROR("服务端执行出现异常");

    private String desc;

    EventType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}