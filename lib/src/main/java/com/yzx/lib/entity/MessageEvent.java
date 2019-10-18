package com.yzx.lib.entity;

import java.util.Map;

/**
 * Created by lyf on 2019/5/23.
 * EvenBus消息类
 */

public class MessageEvent {
    private String key;
    private Object msg;

    public MessageEvent(String key, Object msg) {
        this.key = key;
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
