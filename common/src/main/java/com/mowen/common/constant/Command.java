package com.mowen.common.constant;

/***
 * desc  : com.mowen.common.constant
 * author: mowen
 * create_time: 2019/6/11 11:39
 * project_name : nettyRPC_parent
 */
public interface Command {
    String PING = "ping";
    String PONG = "pong";
    String INVOKE = "invoke";
    String RESPONSE = "response";
}
