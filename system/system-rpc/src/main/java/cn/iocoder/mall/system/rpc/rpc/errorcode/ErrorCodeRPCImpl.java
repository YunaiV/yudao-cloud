package cn.iocoder.mall.system.rpc.rpc.errorcode;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.service.errorcode.ErrorCodeService;
import cn.iocoder.mall.system.rpc.api.errorcode.ErrorCodeRPC;
import cn.iocoder.mall.system.rpc.convert.errorcode.ErrorCodeConvert;
import cn.iocoder.mall.system.rpc.convert.user.UserConvert;
import cn.iocoder.mall.system.rpc.request.errorcode.ErrorCodeAddRequest;
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

    /**
     * 根据分组获取错误码信息
     * @param group 分组
     * @return 错误码信息
     */
    @Override
    public CommonResult<List<ErrorCodeResponse>> getErrorCodeByGroup(Integer group) {
        List<ErrorCodeBO> list =  errorCodeService.getErrorCodeByGroup(group);
        return CommonResult.success(ErrorCodeConvert.INSTANCE.convert(list));
    }

    /**
     * 添加错误码信息，如果是枚举错误码，角色一定是系统内置
     * @param errorCodeAddRequest 错误码
     * @return 是否成功
     */
    @Override
    public CommonResult<Boolean> addErrorCode(ErrorCodeAddRequest errorCodeAddRequest) {
        errorCodeService.addErrorCode(ErrorCodeConvert.INSTANCE.convert(errorCodeAddRequest));
        return CommonResult.success(true);
    }

    /**
     * 批量添加错误码信息
     * @param list 错误码信息集合
     * @return 是否成功
     */
    @Override
    public CommonResult<Boolean> addErrorCodeList(List<ErrorCodeAddRequest> list) {
        errorCodeService.addErrorCodeList(ErrorCodeConvert.INSTANCE.convertList(list));
        return CommonResult.success(true);
    }

    /**
     * 根据分组和角色条件删除错误码信息，只能删除db信息，删除后会进行校验，刷新utils
     * @param group 分组
     * @param type 角色
     * @return 是否成功
     */
    @Override
    public CommonResult<Boolean> deleteErrorCodeByGroup(Integer group, Integer type) {
        return null;
    }
}
