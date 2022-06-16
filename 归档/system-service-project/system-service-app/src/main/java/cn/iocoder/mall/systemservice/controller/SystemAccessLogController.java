package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.systemlog.SystemAccessLogManager;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/accesslog")
public class SystemAccessLogController {
    @Autowired
    private SystemAccessLogManager systemAccessLogManager;

    @PostMapping("createSystemAccessLog")
    public CommonResult<Boolean> createSystemAccessLog(@RequestBody SystemAccessLogCreateDTO createDTO) {
        systemAccessLogManager.createSystemAccessLog(createDTO);
        return success(true);
    }

    @PostMapping("pageSystemAccessLog")
    public CommonResult<PageResult<SystemAccessLogVO>> pageSystemAccessLog(@RequestBody SystemAccessLogPageDTO pageDTO) {
        return success(systemAccessLogManager.pageSystemAccessLog(pageDTO));
    }

}
