package cn.iocoder.mall.system.rpc.api.errorcode;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.response.errorcode.ErrorCodeResponse;

import java.util.List;

/**
 * ErrorCode RPC 接口
 * @author ding
 */
public interface ErrorCodeRPC {

    CommonResult<List<ErrorCodeResponse>> getErrorCode();

}
