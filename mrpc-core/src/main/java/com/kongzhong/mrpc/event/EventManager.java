package com.kongzhong.mrpc.event;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 事件管理器
 *
 * @author biezhi
 *         2017/6/19
 */
public class EventManager {

    private Map<EventType, List<EventListener>> listenerMap;

    public EventManager() {
        this.listenerMap = Stream.of(EventType.values()).collect(Collectors.toMap(v -> v, v -> new LinkedList<>()));
    }

    public void addEventListener(EventType type, EventListener listener) {
        listenerMap.get(type).add(listener);
    }

    public void fireEvent(EventType type) {
        listenerMap.get(type).stream()
                .forEach(listener -> listener.handleEvent(new Event(type)));
    }

}