package com.halo.javamail.utils;

import java.util.UUID;

/**
 * @author 罗铭森
 * @date 2021/8/6 10:11
 * @description TODO(这里用一句话描述这个类的作用)
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
