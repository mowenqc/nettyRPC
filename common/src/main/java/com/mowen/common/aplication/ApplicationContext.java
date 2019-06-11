package com.mowen.common.aplication;

import com.mowen.serializer.CodecFactory;
import com.mowen.serializer.hessian2.Hessian2CodecFactory;
import io.netty.channel.Channel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/***
 * desc  : com.mowen.server
 * author: mowen
 * create_time: 2019/6/10 17:48
 * project_name : nettyRPC_parent
 */
public class ApplicationContext {

    private static Map<String, Channel> CONNECTION = new ConcurrentHashMap<>();

    private static Properties properties;

    private static CodecFactory codecFactory;

    public static void initCodecFactory(CodecFactory factory) {
        codecFactory = factory;
    }

    public static void load(String conf) {
        InputStream resourceAsStream = ApplicationContext.class.getClassLoader()
            .getResourceAsStream(conf);
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static void addConnection(String key, Channel channel) {
        CONNECTION.put(key, channel);
    }

    public static Channel findConnection(String key) {
        return CONNECTION.get(key);
    }

    public static void removeConnection(String key) {
        CONNECTION.remove(key);
    }

    public static CodecFactory findCodecFactory() {
        if (codecFactory == null) {
            return new Hessian2CodecFactory();
        }
        return codecFactory;
    }
}
