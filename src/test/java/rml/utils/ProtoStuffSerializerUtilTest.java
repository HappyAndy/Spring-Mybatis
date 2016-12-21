package rml.utils;

import lombok.Data;
import org.junit.Test;
import rml.utils.protostuff.ProtoStuffSerializerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2016/12/21.
 */
public class ProtoStuffSerializerUtilTest {


    @Data

    private static class Person {
        int id;
        String name;
        Map m;

        Person(int id, String name, Map m) {
            this.id = id;
            this.name = name;
            this.m = m;
        }

    }

    @Test
    public void serialize() throws Exception {
        Map m = new HashMap();
        m.put("2", "121");
        Person p = new Person(1, "ff", m);
        byte[] arr = ProtoStuffSerializerUtil.serialize(p);
        Person result = ProtoStuffSerializerUtil.deserialize(arr, Person.class);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getName(), result.getName());
        System.out.println(result.getM().get("2"));
    }

    @Test
    public void deserialize() throws Exception {

    }

    @Test
    public void serializeList() throws Exception {
        Map m = new HashMap();
        m.put("2", "121");
        Person p = new Person(1, "ff", m);

        List<Person> personlist = new ArrayList<Person>();
        personlist.add(p);
        personlist.add(p);


        byte[] arr = ProtoStuffSerializerUtil.serializeList(personlist);

        List<Person> result = ProtoStuffSerializerUtil.deserializeList(arr, Person.class);
        System.out.println(result.get(0).getId());
        assertEquals(p.getId(), result.get(0).getId());


    }

    @Test
    public void deserializeList() throws Exception {

    }

}