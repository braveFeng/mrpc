package com.kongzhong.mrpc.event;

/**
 * 事件监听器
 *
 * @author biezhi
 *         2017/6/19
 */
@FunctionalInterface
public interface EventListener {

    void handleEvent(Event e);

}