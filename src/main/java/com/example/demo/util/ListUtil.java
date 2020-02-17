package com.example.demo.util;

import java.util.*;

public class ListUtil {
    /**
     * List元素去重
     * */
    public static List removeDuplicate(List list){
        List newList = new ArrayList(new TreeSet(list));
        return newList;
    }
}
