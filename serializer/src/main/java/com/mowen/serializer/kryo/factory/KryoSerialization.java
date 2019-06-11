package com.mowen.serializer.kryo.factory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/***
 * desc  : com.mowen.nettylearn.codec.kryo.serialize
 * author: mowen
 * create_time: 2019/5/31 15:55
 * project_name : rpc_parent
 */
public class KryoSerialization implements Serialization {
    private final KyroFactory kyroFactory;

    public KryoSerialization(final KyroFactory kyroFactory) {
        this.kyroFactory = kyroFactory;
    }

    @Override
    public void serialize(final OutputStream out, final Object message) throws IOException {
        Kryo kryo = kyroFactory.getKryo();
        Output output = new Output(out);
        kryo.writeClassAndObject(output, message);
        output.close();
        kyroFactory.returnKryo(kryo);
    }

    @Override
    public Object deserialize(final InputStream in) throws IOException {
        Kryo kryo = kyroFactory.getKryo();
        Input input = new Input(in);
        Object result = kryo.readClassAndObject(input);
        input.close();
        kyroFactory.returnKryo(kryo);
        return result;
    }
}
