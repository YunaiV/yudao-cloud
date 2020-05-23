package cn.iocoder.mall.system.biz.config;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.service.errorcode.ErrorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
public class ServiceExceptionConfiguration {

    // TODO FROM 芋艿 to 鱿鱼须：这块的实现，微信一起沟通下哈。大体是说，要调用 RPC 接口，不然别的模块无法使用哟。最终，我们是要做成 starter，提供给各个模块用。
    @Autowired
    private ErrorCodeService errorCodeService;

    @EventListener(ApplicationReadyEvent.class) // 可参考 https://www.cnblogs.com/ssslinppp/p/7607509.html
    public void initMessages() {
        errorCodeService.deleteSyStemErrorCode(SystemErrorCodeEnum.ADMIN_NOT_FOUND.getGroup());
        errorCodeService.addSystemErrorCodeList(SystemErrorCodeEnum.values());
        for (SystemErrorCodeEnum item : SystemErrorCodeEnum.values()) {
            ServiceExceptionUtil.put(item.getCode(), item.getMessage());
        }
        for (ErrorCodeBO bo : errorCodeService.getErrorCodeByGroup(SystemErrorCodeEnum.ADMIN_NOT_FOUND.getGroup())) {
            ServiceExceptionUtil.put(bo.getCode(),bo.getMessage());
        }
    }

}
