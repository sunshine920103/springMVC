package cc.study.test;

import cc.study.springmvc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2016/5/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)   //基于junit4的spring测试框架
@ContextConfiguration(locations = {"/spring/dispatcher-servlet.xml"})
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void hasMatchUser(){
        boolean b1=userService.hasMatchUser("admin","123456");
        boolean b2=userService.hasMatchUser("admin","1111");
        assertTrue(b1);
        assertTrue(!b2);

    }
}
