package com.my.util;


import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author zhangyaran
 * @Date 2021/6/3 14:47
 */
public class OutUtil {
    public static String  printForList(List list){
        StringBuilder resStr = new StringBuilder();
        resStr.append("[ ");
        list.stream().forEach(o->resStr.append(o.toString()).append(" ,"));
        resStr.deleteCharAt(resStr.length());
        resStr.append(" ]");
        return resStr.toString();
    }
}
