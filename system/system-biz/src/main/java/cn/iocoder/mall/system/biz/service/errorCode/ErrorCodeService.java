package cn.iocoder.mall.system.biz.service.errorCode;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeAddDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeDeleteDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodePageDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeUpdateDTO;

import java.util.List;

/**
 * @author ding
 */
public interface ErrorCodeService {
    /**
     * 根据code查询错误码
     * @param code code
     * @return 错误码信息
     */
    ErrorCodeBO getErrorCode(Integer code);

    List<ErrorCodeBO> getErrorCodeList();

    PageResult<ErrorCodeBO> getErrorCodePage(ErrorCodePageDTO pageDTO);

    /**
     * 新增
     * @param errorCodeAddDTO 错误码信息，默认类型为自定义错误码
     * @return
     */
    Integer addErrorCode(ErrorCodeAddDTO errorCodeAddDTO);

    /**
     * 更新错误码，系统内置错误码是不允许更新
     * @param errorCodeUpdateDTO 错误码信息
     */
    void updateErrorCode(ErrorCodeUpdateDTO errorCodeUpdateDTO);

    /**
     * 删除错误码
     * @param errorCodeDeleteDTO 只允许删除自定义错误码
     */
    void deleteErrorCode(ErrorCodeDeleteDTO errorCodeDeleteDTO);
}
