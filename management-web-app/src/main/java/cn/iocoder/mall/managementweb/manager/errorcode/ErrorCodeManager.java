package cn.iocoder.mall.managementweb.manager.errorcode;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodeCreateDTO;
import cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodePageDTO;
import cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodeUpdateDTO;
import cn.iocoder.mall.managementweb.controller.errorcode.vo.ErrorCodeVO;
import cn.iocoder.mall.managementweb.convert.errorcode.ErrorCodeConvert;
import cn.iocoder.mall.systemservice.enums.errorcode.ErrorCodeTypeEnum;
import cn.iocoder.mall.systemservice.rpc.errorcode.ErrorCodeRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 错误码 Manager
*/
@Service
public class ErrorCodeManager {

    @Reference(version = "${dubbo.consumer.ErrorCodeRpc.version}", validation = "false")
    private ErrorCodeRpc errorCodeRpc;

    /**
    * 创建错误码
    *
    * @param createDTO 创建错误码 DTO
    * @return 错误码
    */
    public Integer createErrorCode(ErrorCodeCreateDTO createDTO) {
        CommonResult<Integer> createErrorCodeResult = errorCodeRpc.createErrorCode(ErrorCodeConvert.INSTANCE.convert(createDTO)
                .setType(ErrorCodeTypeEnum.MANUAL_OPERATION.getType()));
        createErrorCodeResult.checkError();
        return createErrorCodeResult.getData();
    }

    /**
    * 更新错误码
    *
    * @param updateDTO 更新错误码 DTO
    */
    public void updateErrorCode(ErrorCodeUpdateDTO updateDTO) {
        CommonResult<Boolean> updateErrorCodeResult = errorCodeRpc.updateErrorCode(ErrorCodeConvert.INSTANCE.convert(updateDTO)
                .setType(ErrorCodeTypeEnum.MANUAL_OPERATION.getType()));
        updateErrorCodeResult.checkError();
    }

    /**
    * 删除错误码
    *
    * @param errorCodeId 错误码编号
    */
    public void deleteErrorCode(Integer errorCodeId) {
        CommonResult<Boolean> deleteErrorCodeResult = errorCodeRpc.deleteErrorCode(errorCodeId);
        deleteErrorCodeResult.checkError();
    }

    /**
    * 获得错误码
    *
    * @param errorCodeId 错误码编号
    * @return 错误码
    */
    public ErrorCodeVO getErrorCode(Integer errorCodeId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.errorcode.vo.ErrorCodeVO> getErrorCodeResult = errorCodeRpc.getErrorCode(errorCodeId);
        getErrorCodeResult.checkError();
        return ErrorCodeConvert.INSTANCE.convert(getErrorCodeResult.getData());
    }

    /**
    * 获得错误码列表
    *
    * @param errorCodeIds 错误码编号列表
    * @return 错误码列表
    */
    public List<ErrorCodeVO> listErrorCodes(List<Integer> errorCodeIds) {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.errorcode.vo.ErrorCodeVO>> listErrorCodeResult = errorCodeRpc.listErrorCodes(errorCodeIds);
        listErrorCodeResult.checkError();
        return ErrorCodeConvert.INSTANCE.convertList(listErrorCodeResult.getData());
    }

    /**
    * 获得错误码分页
    *
    * @param pageDTO 错误码分页查询
    * @return 错误码分页结果
    */
    public PageResult<ErrorCodeVO> pageErrorCode(ErrorCodePageDTO pageDTO) {
        CommonResult<PageResult<cn.iocoder.mall.systemservice.rpc.errorcode.vo.ErrorCodeVO>> pageErrorCodeResult
                = errorCodeRpc.pageErrorCode(ErrorCodeConvert.INSTANCE.convert(pageDTO));
        pageErrorCodeResult.checkError();
        return ErrorCodeConvert.INSTANCE.convertPage(pageErrorCodeResult.getData());
    }

}
