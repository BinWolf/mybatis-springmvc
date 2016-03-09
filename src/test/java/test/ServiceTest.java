package test;

import com.wolf.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by wolf on 15/11/18.
 */

public class ServiceTest {
    private ApplicationContext ac = null;

    private IUserService userService;

/*@Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        userService = (IUserService) ac.getBean("userService");
    }*/


    @Test
    public void testSaveUser() throws Exception {
        userService.getUserById("u00000");
    }

    @Test
    public void testGetUserById() {

    }

    @Test
    public void testString() {
        String a = " ";
        System.out.println(a.length());
    }

}
