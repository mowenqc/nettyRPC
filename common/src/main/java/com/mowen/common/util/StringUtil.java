package com.mowen.common.util;

/***
 * desc  : com.mowen.common.util
 * author: mowen
 * create_time: 2019/6/11 10:18
 * project_name : nettyRPC_parent
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        if(str == null || str == ""){
            return true;
        }
        return false;
    }
}
