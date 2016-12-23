package rml.utils.protostuff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor// 必须有无参构造器， 用于反射实例化构建， 可以使用lombok 的注解实现

public class Person {
    int id;
    String name;
    Map m;

}

