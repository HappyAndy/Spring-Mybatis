package rml.service.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rml.dao.MUserMapper;
import rml.model.MUser;
import rml.service.MUserServiceI;

import java.util.List;

@Service("muserService")
public class MUserServiceRedisImpl implements MUserServiceI {

    private static final ObjectMapper mapper = new ObjectMapper();

    private MUserMapper muserMapper;

    public MUserMapper getMuserMapper() {
        return muserMapper;
    }

    @Autowired
    public void setMuserMapper(MUserMapper muserMapper) {
        this.muserMapper = muserMapper;
    }

    private RedisClientTemplate redisClientTemplate;

    public RedisClientTemplate getRedisClientTemplate() {
        return redisClientTemplate;
    }

    @Autowired
    public void setRedisClientTemplate(RedisClientTemplate redisClientTemplate) {
        this.redisClientTemplate = redisClientTemplate;
    }


    public List<MUser> getAll() {

        return muserMapper.getAll();
    }

    public int insert(MUser muser) {

        return muserMapper.insert(muser);
    }

    public int update(MUser muser) {

        return muserMapper.updateByPrimaryKey(muser);
    }

    public int delete(String id) {

        return muserMapper.deleteByPrimaryKey(id);
    }

    public MUser selectByPrimaryKey(String id) {

        return muserMapper.selectByPrimaryKey(id);
    }


    public List<MUser> getMUserByName(String name) {
        String key = "getMUserByName:name:" + name;
        try {
            boolean flag = redisClientTemplate.exists(key);
            if (flag) {
                String userJsonStr = redisClientTemplate.get(key);
                return mapper.readValue(userJsonStr, new TypeReference<List<MUser>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<MUser> list = muserMapper.selectByName(name);
        try {
            String userJsonStr = mapper.writeValueAsString(list);
            redisClientTemplate.setex(key, 60, userJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
