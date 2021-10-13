package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.manager.admin.AdminManager;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {
    @Autowired
    private AdminManager adminManager;

    
    @PostMapping("verifyPassword")
    public CommonResult<AdminVO> verifyPassword(@RequestBody AdminVerifyPasswordDTO verifyPasswordDTO) {
        return success(adminManager.verifyPassword(verifyPasswordDTO));
    }

    @PostMapping("createAdmin")
    public CommonResult<Integer> createAdmin(@RequestBody AdminCreateDTO createDTO) {
        AdminVO adminVO = adminManager.createAdmin(createDTO);
        return success(adminVO.getId());
    }

    @PostMapping("updateAdmin")
    public CommonResult<Boolean> updateAdmin(@RequestBody AdminUpdateDTO updateDTO) {
        adminManager.updateAdmin(updateDTO);
        return success(true);
    }

    @PostMapping("pageAdmin")
    public CommonResult<PageResult<AdminVO>> pageAdmin(@RequestBody  AdminPageDTO pageDTO) {
        return success(adminManager.pageAdmin(pageDTO));
    }

    @GetMapping("getAdmin")
    public CommonResult<AdminVO> getAdmin(@RequestParam("adminId") Integer adminId) {
        return success(adminManager.getAdmin(adminId));
    }

}
