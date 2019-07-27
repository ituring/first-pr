package xyz.xlong99.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.xlong99.dao.LoginDao;
import xyz.xlong99.dao.UserDao;
import xyz.xlong99.utils.SendMessage;
import xyz.xlong99.utils.md5;

import javax.annotation.Resource;

/**
 * @author xlong
 * @date 2019-05-24 21:22
 */

public class xlongtest extends BaseTest {
    @Resource
    private   LoginDao loginDao;

    @Test
    public  void test1() {
        System.out.println(SendMessage.sendMessage("1553619818"));
    }
}
