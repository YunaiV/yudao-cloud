package cn.iocoder.mall.admin.service;

import cn.iocoder.mall.system.api.SystemLogService;
import cn.iocoder.mall.system.api.bo.systemlog.AccessLogPageBO;
import cn.iocoder.mall.system.api.dto.systemlog.AccessLogPageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 18:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemLogServiceImplTest.class)
public class SystemLogServiceImplTest {

    @Autowired
    private SystemLogService systemLogService;

    @Test
    public void getAccessLogPageTest(){
        AccessLogPageDTO accessLogPageDTO = new AccessLogPageDTO();
        accessLogPageDTO.setPageNo(1);
        accessLogPageDTO.setPageSize(10);
        AccessLogPageBO accessLogPage = systemLogService.getAccessLogPage(accessLogPageDTO);
        System.out.println(accessLogPage.getTotal());

    }


}
