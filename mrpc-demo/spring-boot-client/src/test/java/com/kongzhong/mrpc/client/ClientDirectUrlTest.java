package com.kongzhong.mrpc.client;

import com.kongzhong.mrpc.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author biezhi
 *         19/06/2017
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootClientApplication.class, value = {
        "mrpc.test=true"
})
@ActiveProfiles("directurl")
public class ClientDirectUrlTest {

    @Autowired
    private UserService userService;

    @Test
    public void testHello() {
        String msg = userService.hello("direct-url");
        Assert.assertEquals("Hello, direct-url", msg);
    }

}
