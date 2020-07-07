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

    @Autowired
    private ResourceMapper resourceMapper;

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

    @Override
    public int countResource(ResourceCountDTO countDTO) {
        return resourceMapper.selectCountByIdsAndType(countDTO.getIds(), countDTO.getType());
    }

    @Override
    public List<ResourceTreeNodeBO> getResourceTree(ResourceGetTreeDTO getTreeDTO) {
        // 获得对应的资源列表
        List<ResourceDO> resourceDOs = resourceMapper.selectListByIdsAndType(getTreeDTO.getIds(), getTreeDTO.getType());
        // 拼装成树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Integer, ResourceTreeNodeBO> treeNodeMap = new LinkedHashMap<>();
        resourceDOs.stream().sorted(Comparator.comparing(ResourceDO::getSort))
                .forEach(resourceDO -> treeNodeMap.put(resourceDO.getId(), ResourceConvert.INSTANCE.convertTreeNode(resourceDO)));
        // 处理父子关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getNode().getPid().equals(ResourceIdEnum.ROOT.getId()))
                .forEach((childNode) -> {
                    // 获得父节点
                    ResourceTreeNodeBO parentNode = treeNodeMap.get(childNode.getNode().getPid());
                    if (parentNode == null) {
                        log.error("[getResourceTree][resource({}) 找不到父资源({})]", childNode.getNode().getId(), childNode.getNode().getPid());
                        return;
                    }
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        return treeNodeMap.values().stream()
                .filter(node -> node.getNode().getPid().equals(ResourceIdEnum.ROOT.getId()))
                .collect(Collectors.toList());
    }

}
