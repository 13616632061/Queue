package com.yzx.lib.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/29.
 * EventBusMap 传值工具类
 */

public class EventBusMapUtil {
    public static Map<Object, Object> getObjectMap(String key, Object value) {
        Map<Object, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
