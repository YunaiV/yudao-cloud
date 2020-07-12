package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.convert.authorization.ResourceConvert;
import cn.iocoder.mall.system.biz.dao.authorization.ResourceMapper;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import cn.iocoder.mall.system.biz.dto.authorization.*;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.enums.authorization.ResourceIdEnum;
import cn.iocoder.mall.system.biz.enums.authorization.ResourceTypeEnum;
import cn.iocoder.mall.system.biz.event.authorization.ResourceDeleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<ResourceBO> getResourcesByPermissions(Collection<String> permissions) {
        List<ResourceDO> resourceDOs = resourceMapper.selectListByPermissions(permissions);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    @Override
    public List<ResourceBO> getResources(ResourceGetListDTO getListDTO) {
        List<ResourceDO> resourceDOs = resourceMapper.selectListByIdsAndType(getListDTO.getIds(), getListDTO.getType());
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }



}
