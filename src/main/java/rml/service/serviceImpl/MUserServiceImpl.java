package rml.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.MUserMapper;
import rml.model.MUser;
import rml.service.MUserServiceI;

@Service("muserService1")
public class MUserServiceImpl implements MUserServiceI {

    private MUserMapper muserMapper;

    public MUserMapper getMuserMapper() {
        return muserMapper;
    }

    @Autowired
    public void setMuserMapper(MUserMapper muserMapper) {
        this.muserMapper = muserMapper;
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

    public List<MUser> getMUserByName(String name) {
        return null;
    }

    public MUser selectByPrimaryKey(String id) {

        return muserMapper.selectByPrimaryKey(id);
    }

}
