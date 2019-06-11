package com.mowen.registry;

import java.io.Serializable;

/***
 * desc  : com.mowen.registry
 * author: mowen
 * create_time: 2019/6/11 14:12
 * project_name : nettyRPC_parent
 */
public class RegistryConfig implements Serializable {

    /**
     * zk地址
     */
    private String zkAddress;

    /**
     * 重试次数
     */
    private int retryTimes;

    /**
     * 重试间隔
     */
    private int retryDuration;

    public RegistryConfig(String zkAddress){
        this(zkAddress, 3, 1000);
    }

    public RegistryConfig(String zkAddress, int retryTimes, int retryDuration){
        this.zkAddress = zkAddress;
        this.retryDuration = retryDuration;
        this.retryTimes = retryTimes;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getRetryDuration() {
        return retryDuration;
    }

    public void setRetryDuration(int retryDuration) {
        this.retryDuration = retryDuration;
    }
}
