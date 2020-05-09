package cn.iocoder.mall.system.biz.config;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dataobject.errorcode.ErrorCodeDO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.service.errorCode.ErrorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
public class ServiceExceptionConfiguration {

//    @Autowired
//    private ErrorCodeService errorCodeService;

    @EventListener(ApplicationReadyEvent.class) // 可参考 https://www.cnblogs.com/ssslinppp/p/7607509.html
    public void initMessages() {
//        List<ErrorCodeBO> list = errorCodeService.getErrorCodeList();
        for (SystemErrorCodeEnum item : SystemErrorCodeEnum.values()) {
            ServiceExceptionUtil.put(item.getCode(), item.getMessage());
        }
//        for (ErrorCodeBO bo : list) {
//            ServiceExceptionUtil.put(bo.getCode(),bo.getMessage());
//        }
    }

}
