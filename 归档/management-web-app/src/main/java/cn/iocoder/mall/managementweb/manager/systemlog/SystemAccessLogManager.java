package cn.iocoder.mall.managementweb.manager.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemAccessLogPageDTO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemAccessLogVO;
import cn.iocoder.mall.managementweb.convert.systemlog.SystemAccessLogConvert;
import cn.iocoder.mall.systemservice.rpc.systemlog.SystemAccessLogFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 系统访问日志 Manager
*/
@Service
public class SystemAccessLogManager {

    @Autowired
    private SystemAccessLogFeign systemAccessLogFeign;
    /**
    * 获得系统访问日志分页
    *
    * @param pageDTO 系统访问日志分页查询
    * @return 系统访问日志分页结果
    */
    public PageResult<SystemAccessLogVO> pageSystemAccessLog(SystemAccessLogPageDTO pageDTO) {
        CommonResult<PageResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO>> pageSystemAccessLogResult =
                systemAccessLogFeign.pageSystemAccessLog(SystemAccessLogConvert.INSTANCE.convert(pageDTO));
        pageSystemAccessLogResult.checkError();
        return SystemAccessLogConvert.INSTANCE.convertPage(pageSystemAccessLogResult.getData());
    }

}
