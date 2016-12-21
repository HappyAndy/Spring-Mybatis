package rml.utils.protostuff;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 同样为了提高Protostuff序列化/反序列化类的利用效率，
 * 我们可以对其进行池化处理，而不要频繁的创建、销毁对象。
 * @author tangjie<https://github.com/tang-jie>
 * @filename:ProtostuffSerializeFactory.java
 * @description:ProtostuffSerializeFactory功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2016/10/7
 */
public class ProtostuffSerializeFactory extends BasePooledObjectFactory<ProtostuffSerialize> {

    public ProtostuffSerialize create() throws Exception {
        return createProtostuff();
    }

    public PooledObject<ProtostuffSerialize> wrap(ProtostuffSerialize hessian) {
        return new DefaultPooledObject<ProtostuffSerialize>(hessian);
    }

    private ProtostuffSerialize createProtostuff() {
        return new ProtostuffSerialize();
    }
}