package com.ydh.eventbus.event;

/**
 * 粘性事件消息类
 * @author 13001
 */
public class StickyEvent {

    String name;
    String id;

    public StickyEvent(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
