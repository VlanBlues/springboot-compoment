package com.example.component.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Author: Vlan
 * Date: 2019/10/22 9:36
 */
public class RandomUtil {
    
    public static String getRandom(){
        SecureRandom rand = new SecureRandom();
        String num = "";
        for(int i=0;i<5;i++){
            num += rand.nextInt(10);
        }
        return num;
    }

    public static String getTenRandom(){
        SecureRandom rand = new SecureRandom();
        StringBuilder num = new StringBuilder();
        for(int i=0;i<10;i++){
            num.append(rand.nextInt(10));
        }
        return num.toString();
    }
}
