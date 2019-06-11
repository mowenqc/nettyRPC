package com.mowen.serializer.kryo.factory;

import com.esotericsoftware.kryo.Kryo;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/***
 * desc  : com.mowen.nettylearn.codec.kryo.serialize
 * author: mowen
 * create_time: 2019/5/31 15:50
 * project_name : rpc_parent
 */
public class KyroFactory {
    private final GenericObjectPool<Kryo> kryoPool;

    public KyroFactory() {
        kryoPool = new GenericObjectPool<>(new PooledKryoFactory());
    }

    public KyroFactory(final int maxTotal, final int minIdle, final long maxWaitMillis, final long minEvictableIdleTimeMillis) {
        kryoPool = new GenericObjectPool<>(new PooledKryoFactory());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        kryoPool.setConfig(config);
    }

    public Kryo getKryo() {
        try {
            return kryoPool.borrowObject();
        } catch (final Exception ex) {

        }
        return null;
    }

    public void returnKryo(final Kryo kryo) {
        kryoPool.returnObject(kryo);
    }
}
