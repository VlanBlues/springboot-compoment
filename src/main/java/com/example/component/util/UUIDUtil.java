package com.example.component.util;

import java.util.UUID;

/**
 * Author: Vlan
 * Date: 2021/2/2 14:19
 */
public class UUIDUtil {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }
}
