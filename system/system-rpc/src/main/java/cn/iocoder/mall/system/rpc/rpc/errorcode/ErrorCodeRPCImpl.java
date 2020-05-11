package cn.iocoder.mall.system.rpc.rpc.errorcode;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.service.errorcode.ErrorCodeService;
import cn.iocoder.mall.system.rpc.api.errorcode.ErrorCodeRPC;
import cn.iocoder.mall.system.rpc.convert.errorcode.ErrorCodeConvert;
import cn.iocoder.mall.system.rpc.convert.user.UserConvert;
import cn.iocoder.mall.system.rpc.response.errorcode.ErrorCodeResponse;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author ding
 */
@Service(version = "${dubbo.provider.ErrorCodeRPC.version}", validation = "true")
public class ErrorCodeRPCImpl implements ErrorCodeRPC {

    @Autowired
    private ErrorCodeService errorCodeService;

    @Override
    public CommonResult<List<ErrorCodeResponse>> getErrorCode() {
        List<ErrorCodeBO> list =  errorCodeService.getErrorCodeListAll();
        return CommonResult.success(ErrorCodeConvert.INSTANCE.convert(list));
    }
}
