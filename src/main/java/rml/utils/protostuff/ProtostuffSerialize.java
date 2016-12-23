package rml.utils.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;

import java.io.*;
import java.util.List;


public class ProtostuffSerialize {
    private static SchemaCache cachedSchema = SchemaCache.getInstance();

    private static <T> Schema<T> getSchema(Class<T> cls) {
        return (Schema<T>) cachedSchema.get(cls);
    }

    /**
     * 序列化单个对象
     *
     * @param input
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T deserialize(InputStream input, Class<T> targetClass) {
        try {

            T instance = targetClass.newInstance();

            Schema<T> schema = getSchema(targetClass);
            ProtostuffIOUtil.mergeFrom(input, instance, schema);
            return instance;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 反序列化但对象
     *
     * @param output
     * @param object
     */
    public void serialize(OutputStream output, Object object) {
        Class cls = (Class) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = getSchema(cls);
            ProtostuffIOUtil.writeTo(output, object, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 序列化List集合
     *  @param output
     * @param objList
     */
    public   <T> void serializeList(ByteArrayOutputStream output, List<T> objList) {
        if (objList == null || objList.isEmpty()) {
            throw new RuntimeException("序列化对象列表(" + objList + ")参数异常!");
        }
        Class cls = (Class) objList.get(0).getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = getSchema(cls);
            ProtostuffIOUtil.writeListTo(output, objList, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }

    }

    /**
     * 反序列化集合
     *
     * @param byteArrayInputStream
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> List<T> deserializeList(ByteArrayInputStream byteArrayInputStream, Class<T> targetClass) {
        if (byteArrayInputStream == null) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }
        List<T> result = null;
        try {
            T instance = targetClass.newInstance();
            Schema<T> schema = getSchema(targetClass);
            result = ProtostuffIOUtil.parseListFrom(byteArrayInputStream, schema);
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象列表发生异常!", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}