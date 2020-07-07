package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.convert.authorization.RoleConvert;
import cn.iocoder.mall.system.biz.dao.authorization.RoleMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import cn.iocoder.mall.system.biz.dto.authorization.*;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.enums.authorization.RoleCodeEnum;
import cn.iocoder.mall.system.biz.enums.authorization.RoleTypeEnum;
import cn.iocoder.mall.system.biz.event.authorization.ResourceDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult<RoleBO> getRolePage(RolePageDTO pageDTO) {
        return RoleConvert.INSTANCE.convertPage(roleMapper.selectPage(pageDTO));
    }

    @Override
    public boolean hasSuperAdmin(Collection<Integer> ids) {
        List<RoleDO> roleDOs = roleMapper.selectBatchIds(ids);
        for (RoleDO roleDO : roleDOs) {
            if (RoleCodeEnum.SUPER_ADMIN.getCode().equals(roleDO.getCode())) {
                return true;
            }
        }
        return false;
    }

}
