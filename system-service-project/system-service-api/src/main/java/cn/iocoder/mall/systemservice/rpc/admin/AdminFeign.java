package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@FeignClient("system-service")
public interface AdminFeign {
    @PostMapping("/system/admin/verifyPassword")
    public CommonResult<AdminVO> verifyPassword(@RequestBody AdminVerifyPasswordDTO verifyPasswordDTO) ;

    @PostMapping("/system/admin/createAdmin")
    public CommonResult<Integer> createAdmin(@RequestBody AdminCreateDTO createDTO) ;

    @PostMapping("/system/admin/updateAdmin")
    public CommonResult<Boolean> updateAdmin(@RequestBody AdminUpdateDTO updateDTO) ;

    @PostMapping("/system/admin/pageAdmin")
    public CommonResult<PageResult<AdminVO>> pageAdmin(@RequestBody AdminPageDTO pageDTO);

    @GetMapping("/system/admin/getAdmin")
    public CommonResult<AdminVO> getAdmin(@RequestParam("adminId") Integer adminId) ;

}
