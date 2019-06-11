package com.mowen.registry.curator;

import com.mowen.common.zk.Zkclient;
import com.mowen.registry.RegistryConfig;
import java.util.List;
import java.util.Objects;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/***
 * desc  : com.mowen.registry.curator
 * author: mowen
 * create_time: 2019/6/11 14:26
 * project_name : nettyRPC_parent
 */
public class CuratorClient  implements Zkclient {

    private Logger logger = Logger.getLogger(getClass());
    /**
     * zkConfig
     */
    private RegistryConfig registryConfig;

    private CuratorFramework client;

    public CuratorClient(RegistryConfig config){
        this.registryConfig = config;
    }


    public void start(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(registryConfig.getRetryDuration(), registryConfig.getRetryTimes());
        client = CuratorFrameworkFactory
            .newClient(registryConfig.getZkAddress(), retryPolicy);
        client.start();
    }
    @Override
    public void create(String path, CreateMode createMode) {
        try {
            Objects.requireNonNull(path);
            client.create().creatingParentContainersIfNeeded().withMode(createMode).forPath(path);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void create(String path, String data, CreateMode createMode) {
        try {
            Objects.requireNonNull(path);
            client.create().creatingParentContainersIfNeeded().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getChildren(String path) {
        try {
            Objects.requireNonNull(path);
            List<String> list = client.getChildren().forPath(path);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String getData(String path) {
        try {
            Objects.requireNonNull(path);
            byte[] bytes = client.getData().forPath(path);
            if(bytes != null){
                return new String(bytes);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    @Override
    public void delete(String path) {
        try {
            Objects.requireNonNull(path);
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void setData(String path, String data) {
        try {
            Objects.requireNonNull(path);
            Objects.requireNonNull(data);
            client.setData().forPath(path,data.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public boolean checkPath(String path) {
        try {
            Objects.requireNonNull(path);
            Stat path1 = client.checkExists().forPath(path);
            if(path1 != null){
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void addNodeListener(String path, NodeCacheListener nodeCacheListener) {
        try {
            Objects.requireNonNull(path);
            NodeCache nodeCache = new NodeCache(client, path);
            nodeCache.start();
            nodeCache.getListenable().addListener(nodeCacheListener);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void addNodeListener(String path, TreeCacheListener treeCacheListener) {
        try {
            Objects.requireNonNull(path);
            TreeCache treeCache = new TreeCache(client, path);
            treeCache.getListenable().addListener(treeCacheListener);
            treeCache.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
