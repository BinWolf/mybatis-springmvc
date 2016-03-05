package test;

import com.wolf.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by wolf on 15/11/18.
 */
public class ServiceTest {
    private ApplicationContext ac = null;

    @Autowired
    private IUserService userService;

    /*@Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        userService = (IUserService) ac.getBean("userService");
    }*/


    @Test
    public void testSaveUser() throws Exception{

    }

    @Test
    public void testGetUserById() {

    }

}
