package com.mowen.common.zk;

import java.util.List;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;

/***
 * desc  : com.mowen.common.zk
 * author: mowen
 * create_time: 2019/6/11 17:42
 * project_name : nettyRPC_parent
 */
public interface Zkclient {

    /**
     * 创建目录， 默认数据为空
     * @param path
     * @param createMode
     */
    void create(String path, CreateMode createMode);

    /**
     * 创建目录, 并设置path的数据
     * @param path
     * @param createMode
     */
    void create(String path,String data,  CreateMode createMode);

    /**
     * 获取子目录
     * @param path
     * @return
     */
    List<String> getChildren(String path);

    /**
     * 删除节点
     * @param path
     */
    void delete(String path);

    /**
     * 获取节点下的数据
     * @param path
     * @return
     */
    String getData(String path);

    /**
     * 设置数据
     * @param path
     * @param data
     */
    void setData(String path, String data);

    /**
     * 检查path时候存在
     * @param path path
     * @return
     */
    boolean checkPath(String path);

    /**
     * 添加节点的监听器
     * 监听本节点的变化  节点可以进行修改操作  删除节点后会再次创建
     * @param path
     * @param nodeCacheListener
     */
    void addNodeListener(String path, NodeCacheListener nodeCacheListener);

    /**
     * 添加节点的监听器
     * 监控 指定节点和节点下的所有的节点的变化--无限监听  可以进行本节点的删除
     * @param path
     * @param treeCacheListener
     */
    void addNodeListener(String path, TreeCacheListener treeCacheListener);
}
