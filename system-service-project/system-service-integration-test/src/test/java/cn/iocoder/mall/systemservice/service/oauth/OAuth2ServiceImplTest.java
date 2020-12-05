package cn.iocoder.mall.systemservice.service.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2ServiceImplTest {

    @Autowired
    private OAuth2ServiceImpl oauth2Service;

    @Test
    public void testCheckAccessToken() {
        oauth2Service.checkAccessToken("yunai");
    }

}
