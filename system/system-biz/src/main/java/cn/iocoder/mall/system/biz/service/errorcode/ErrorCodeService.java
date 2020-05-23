package cn.iocoder.mall.system.biz.service.errorcode;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dataobject.errorcode.ErrorCodeDO;
import cn.iocoder.mall.system.biz.dto.errorcode.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ding
 */
public interface ErrorCodeService extends IService<ErrorCodeDO>{
    /**
     * 根据code查询错误码
     * @param code code
     * @return 错误码信息
     */
    ErrorCodeBO getErrorCode(Integer code);

    /**
     * 从db取出错误码列表数据
     * @return  db错误码列表
     */
    List<ErrorCodeBO> getErrorCodeList(ErrorCodeGetListDTO errorCodeGetListDTO);

    /**
     * 取出所有错误码列表数据
     * @param group 分组标示
     * @return  所有错误码列表
     */
    List<ErrorCodeBO> getErrorCodeByGroup(Integer group);

    /**
     * 分页取出所有错误码列表数据
     * @param pageDTO 分页数据
     * @return 错误码列表
     */
    PageResult<ErrorCodeBO> getErrorCodePage(ErrorCodePageDTO pageDTO);

    /**
     * 新增
     * @param errorCodeAddDTO 错误码信息，默认类型为自定义错误码
     * @return
     */
    Integer addErrorCode(ErrorCodeAddDTO errorCodeAddDTO);

    /**
     * 批量添加错误码信息
     * @param list 错误码集合
     * @return
     */
    Boolean addErrorCodeList(List<ErrorCodeAddDTO> list);

    /**
     * 批量添加错误码信息，项目启动时初始化错误码信息。
     * @param enumerable    错误码枚举类
     * @return 是否成功
     */
    Boolean addSystemErrorCodeList(ServiceExceptionUtil.Enumerable[] enumerable);

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

    /**
     * 删除分组下的错误码，只提供给服务初始化时候
     * @param group 分组
     */
    void deleteSyStemErrorCode(int group);
}
