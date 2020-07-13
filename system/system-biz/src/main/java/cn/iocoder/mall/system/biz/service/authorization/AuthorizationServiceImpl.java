package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dao.authorization.AccountRoleMapper;
import cn.iocoder.mall.system.biz.dao.authorization.RoleResourceMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.AccountRoleDO;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleResourceDO;
import cn.iocoder.mall.system.biz.dto.authorization.*;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.event.authorization.ResourceDeleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.AUTHORIZATION_PERMISSION_DENY;

/**
 * 授权模块 - Service 实现类
 */
@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {


    @EventListener
    public void handleResourceDeleteEvent(ResourceDeleteEvent event) {
        roleResourceMapper.deleteByResourceId(event.getId());
    }

    @EventListener
    public void handleRoleDeleteEvent(ResourceDeleteEvent event) {
        // 标记删除 RoleResource
        roleResourceMapper.deleteByRoleId(event.getId());
        // 标记删除 AdminRole
        accountRoleMapper.deleteByRoleId(event.getId());
    }

}
