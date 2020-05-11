package cn.iocoder.mall.system.rest.request.errorcode;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author ding
 */
@ApiModel("管理员 - 错误码模块 - 错误码分页 Request")
@Data
@Accessors(chain = true)
public class ErrorCodePageRequest {

    // TODO FROM 芋艿 to 鱿鱼须：分页参数？
    // TODO FROM 芋艿 to 鱿鱼须：对于 rest 的接口，要区分下是给 Admins 管理员还是 Users 用户的
}
