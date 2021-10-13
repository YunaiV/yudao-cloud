package cn.iocoder.mall.systemservice.rpc.systemlog;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface SystemAccessLogFeign {

    @PostMapping("/system/accesslog/createSystemAccessLog")
    public CommonResult<Boolean> createSystemAccessLog(@RequestBody SystemAccessLogCreateDTO createDTO);

    @PostMapping("/system/accesslog/pageSystemAccessLog")
    public CommonResult<PageResult<SystemAccessLogVO>> pageSystemAccessLog(@RequestBody SystemAccessLogPageDTO pageDTO);

}