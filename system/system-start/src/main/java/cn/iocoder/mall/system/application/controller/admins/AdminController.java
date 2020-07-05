package cn.iocoder.mall.system.application.controller.admins;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.AdminService;
import cn.iocoder.mall.system.api.DeptmentService;
import cn.iocoder.mall.system.api.ResourceService;
import cn.iocoder.mall.system.api.RoleService;
import cn.iocoder.mall.system.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.system.api.bo.resource.ResourceBO;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.bo.admin.AdminBO;
import cn.iocoder.mall.system.api.constant.ResourceConstants;
import cn.iocoder.mall.system.api.dto.admin.*;
import cn.iocoder.mall.system.application.convert.AdminConvert;
import cn.iocoder.mall.system.application.convert.ResourceConvert;
import cn.iocoder.mall.system.application.vo.admin.AdminMenuTreeNodeVO;
import cn.iocoder.mall.system.application.vo.admin.AdminRoleVO;
import cn.iocoder.mall.system.application.vo.admin.AdminVO;
import cn.iocoder.mall.system.sdk.annotation.RequiresPermissions;
import cn.iocoder.mall.system.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/admin")
@Api("管理员模块")
public class AdminController {

    @Reference(validation = "true", version = "${dubbo.provider.ResourceService.version}")
    private ResourceService resourceService;

    @Reference(validation = "true", version = "${dubbo.provider.AdminService.version}")
    private AdminService adminService;

    @Reference(validation = "true", version = "${dubbo.provider.RoleService.version}")
    private RoleService roleService;

    @Autowired
    private DeptmentService deptmentService;



}
