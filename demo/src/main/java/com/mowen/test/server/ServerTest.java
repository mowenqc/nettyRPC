package com.mowen.test.server;

import com.mowen.common.message.ResMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.naming.Name;

/***
 * desc  : com.mowen.test.server
 * author: mowen
 * create_time: 2019/6/11 8:12
 * project_name : nettyRPC_parent
 */
public class ServerTest {

    public static void main(String[] args) {
        ResMessage resMessage = ResMessage.builder().clazz(Name.class).build();
        System.out.println(resMessage);

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost.getHostAddress());
            System.out.println(localHost.getHostName());
            System.out.println(localHost.getCanonicalHostName());
            System.out.println(new String(localHost.getAddress()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
