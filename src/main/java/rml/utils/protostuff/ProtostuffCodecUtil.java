package rml.utils.protostuff;

import com.google.common.io.Closer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/***
 * 序列化工具类V2.0版本
 * 假如池化，提升性能
 */

public class ProtostuffCodecUtil {
    private static Closer closer = Closer.create();
    private static ProtostuffSerializePool pool = ProtostuffSerializePool.getProtostuffPoolInstance();


    public static byte[] serialize(final Object obj) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            ProtostuffSerialize protostuffSerialization = pool.borrow();
            protostuffSerialization.serialize(byteArrayOutputStream, obj);
            byte[] body = byteArrayOutputStream.toByteArray();
            pool.restore(protostuffSerialization);
            return body;
        } finally {
            closer.close();
        }
    }

    public static <T> T deserialize(byte[] body, Class<T> targetClass) throws IOException {
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


    public static <T> byte[]  serializeList(List<T> objList) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            ProtostuffSerialize protostuffSerialization = pool.borrow();
            protostuffSerialization.serializeList(byteArrayOutputStream, objList);
            byte[] body = byteArrayOutputStream.toByteArray();
            pool.restore(protostuffSerialization);
            return body;
        } finally {
            closer.close();
        }
    }


    public static <T> List<T> deserializeList(byte[] paramArrayOfByte, Class<T> targetClass) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
            closer.register(byteArrayInputStream);
            ProtostuffSerialize protostuffSerialization = pool.borrow();
            List<T>  obj = protostuffSerialization.deserializeList(byteArrayInputStream, targetClass);
            pool.restore(protostuffSerialization);
            return obj;
        } finally {
            closer.close();
        }
    }


}