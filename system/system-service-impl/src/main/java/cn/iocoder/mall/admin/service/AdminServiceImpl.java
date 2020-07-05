package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.enums.DeletedStatusEnum;
import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.AdminService;
import cn.iocoder.mall.system.api.bo.admin.AdminBO;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.constant.AdminConstants;
import cn.iocoder.mall.system.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.system.api.dto.admin.*;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;
import cn.iocoder.mall.admin.convert.AdminConvert;
import cn.iocoder.mall.admin.dao.AdminMapper;
import cn.iocoder.mall.admin.dao.AdminRoleMapper;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import cn.iocoder.mall.admin.dataobject.AdminRoleDO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.AdminService.version}")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private OAuth2ServiceImpl oauth2Service;
    @Autowired
    private RoleServiceImpl roleService;



}
