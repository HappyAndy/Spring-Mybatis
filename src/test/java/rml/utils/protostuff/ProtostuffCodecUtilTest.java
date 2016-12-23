package rml.utils.protostuff;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/22.
 */
public class ProtostuffCodecUtilTest {


    @Test
    public void encode() throws Exception {
        Map m = new HashMap();
        m.put("1", "2");
        Person person = new Person(1, "zyk", m);
        byte[] b = ProtostuffCodecUtil.serialize(person);
        Person p = ProtostuffCodecUtil.deserialize(b, Person.class);
        System.out.println(p.getId());

    }


    @Test
    public void encodeList() throws Exception {
        Map m = new HashMap();
        m.put("1", "2");
        Person person = new Person(1, "zyk", m);
        List<Person> pls = new ArrayList<Person>();
        pls.add(person);
        pls.add(person);
        byte[] bytes = ProtostuffCodecUtil.serializeList(pls);


        List<Person> ps = ProtostuffCodecUtil.deserializeList(bytes, Person.class);
        System.out.println(ps.get(0).getName());


    }

    @Test
    public void deserializeList() throws Exception {

    }

}