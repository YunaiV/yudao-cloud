package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.convert.authorization.RoleConvert;
import cn.iocoder.mall.system.biz.dao.authorization.RoleMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import cn.iocoder.mall.system.biz.enums.authorization.RoleCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleBO> getRoleList(Collection<Integer> ids) {
        List<RoleDO> roleDOs = roleMapper.selectBatchIds(ids);
        return RoleConvert.INSTANCE.convertList(roleDOs);
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
