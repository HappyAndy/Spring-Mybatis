package rml.dao;

import org.apache.ibatis.annotations.Param;
import rml.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * 在传统的jdbc操作中，我们需要手动管理数据库连接的开关，数据库资源访问的开关等等
 * <br/>但是,我们这里采用了Mybatis和Druid这两个框架，那么我们可以完全不必理会数据库连接等等的控制，
 * <br/>我们只需要更加专注于业务实现的开发。
 */
public interface UserDao extends Dao<User> {
    int add(User user);

    int del(User user);

    int update(User user);

    User findOneById(Serializable Id);

    List<User> findAll();

    void updateLoginSession(@Param("sessionId") String sessionId,@Param("loginId")  String loginId);

    void addSessionId(String id);
}
