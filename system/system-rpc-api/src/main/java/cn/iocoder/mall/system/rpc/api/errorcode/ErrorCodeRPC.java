package cn.iocoder.mall.system.rpc.api.errorcode;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.request.errorcode.ErrorCodeAddRequest;
import cn.iocoder.mall.system.rpc.request.systemlog.ExceptionLogAddRequest;
import cn.iocoder.mall.system.rpc.response.errorcode.ErrorCodeResponse;

import java.util.List;

/**
 * ErrorCode RPC 接口
 * 提供其他服务初始化加载错误码到db中，同时提供读取该服务的错误码信息，
 * 同时提供删除接口，
 * @author ding
 */
public interface ErrorCodeRPC {

    CommonResult<List<ErrorCodeResponse>> getErrorCodeByGroup(Integer group);

    CommonResult<Boolean> addErrorCode(ErrorCodeAddRequest errorCodeAddRequest);

    CommonResult<Boolean> addErrorCodeList(List<ErrorCodeAddRequest> list);

    CommonResult<Boolean> deleteErrorCodeByGroup(Integer group, Integer type);

}
