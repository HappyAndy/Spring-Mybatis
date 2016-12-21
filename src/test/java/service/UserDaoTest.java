package service;

import base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rml.exception.OtherThingsException;
import rml.model.User;
import rml.service.serviceImpl.UserServiceImpl;

/**
 * Created by Administrator on 2016/9/25.
 */
class UserServiceTest extends BaseTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testAdd() {
        User user = new User();
        try {
            userService.add(user);
        } catch (OtherThingsException e) {
            //其他综合异常或是不能处理的异常
            e.printStackTrace();
        }


    }
}
