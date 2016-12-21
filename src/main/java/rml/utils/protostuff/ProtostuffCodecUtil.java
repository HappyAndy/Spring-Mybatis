package rml.utils.protostuff;

import com.google.common.io.Closer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:ProtostuffCodecUtil.java
 * @description:ProtostuffCodecUtil功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2016/10/7
 */
public class ProtostuffCodecUtil {
    private static Closer closer = Closer.create();
    private ProtostuffSerializePool pool = ProtostuffSerializePool.getProtostuffPoolInstance();


    public byte[] encode(final Object message) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            ProtostuffSerialize protostuffSerialization = pool.borrow();
            protostuffSerialization.serialize(byteArrayOutputStream, message);
            byte[] body = byteArrayOutputStream.toByteArray();
            pool.restore(protostuffSerialization);
            return body;
        } finally {
            closer.close();
        }
    }

    public <T> T decode(byte[] body, Class<T> targetClass) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            closer.register(byteArrayInputStream);
            ProtostuffSerialize protostuffSerialization = pool.borrow();

            T obj = protostuffSerialization.deserialize(byteArrayInputStream, targetClass);
            pool.restore(protostuffSerialization);
            return obj;
        } finally {
            closer.close();
        }
    }
}