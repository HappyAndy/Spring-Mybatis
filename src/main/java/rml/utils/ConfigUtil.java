package rml.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class ConfigUtil {
    static String conName = "BaseConfig.properties";
    static {
        try {
            InputStream in = ConfigUtil.class.getClass().getClassLoader().getResourceAsStream(conName);
            Properties props = new Properties();
            props.load(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
