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

    @Override
    public Integer addResource(ResourceAddDTO addDTO) {
        // 校验父资源存在
        checkParentResource(addDTO.getPid(), null);
        // 校验资源（自己）
        checkResource(addDTO.getPid(), addDTO.getName(), null);
        // 存储到数据库
        ResourceDO resource = ResourceConvert.INSTANCE.convert(addDTO);
        initResourceProperty(resource);
        resource.setCreateTime(new Date());
        resource.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        resourceMapper.insert(resource);
        // TODO 操作日志
        // 返回成功
        return resource.getId();
    }

    @Override
    public void updateResource(ResourceUpdateDTO updateDTO) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(updateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_NOT_EXISTS);
        }
        // 校验父资源存在
        checkParentResource(updateDTO.getPid(), updateDTO.getId());
        // 校验资源（自己）
        checkResource(updateDTO.getPid(), updateDTO.getName(), updateDTO.getId());
        // 更新到数据库
        ResourceDO resource = ResourceConvert.INSTANCE.convert(updateDTO);
        initResourceProperty(resource);
        resourceMapper.updateById(resource);
        // TODO 操作日志
    }

    @Override
    @Transactional
    public void deleteResource(ResourceDeleteDTO deleteDTO) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(deleteDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_NOT_EXISTS);
        }
        // 校验是否还有子资源
        if (resourceMapper.selectCountByPid(deleteDTO.getId()) > 0) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_EXISTS_CHILDREN);
        }
        // 更新到数据库
        resourceMapper.deleteById(deleteDTO.getId());
        // 发布资源删除事件，方便清理关联表
        eventPublisher.publishEvent(new ResourceDeleteEvent(this, deleteDTO.getId()));
    }

    /**
     * 校验父资源是否合法
     *
     * 1. 不能舌质红自己为父资源
     * 2. 父资源不存在
     * 3. 父资源必须是 {@link ResourceTypeEnum#MENU} 菜单类型
     *
     * @param pid 父资源编号
     * @param childId 当前资源编号
     */
    private void checkParentResource(Integer pid, Integer childId) {
        if (pid == null || ResourceIdEnum.ROOT.getId().equals(pid)) {
            return;
        }
        if (pid.equals(childId)) { // 不能设置自己为父资源
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_PARENT_ERROR);
        }
        ResourceDO resource = resourceMapper.selectById(pid);
        if (resource == null) { // 父资源不存在
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_PARENT_NOT_EXISTS);
        }
        if (!ResourceTypeEnum.MENU.getType().equals(resource.getType())) { // 父资源必须是菜单类型
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_PARENT_NOT_MENU);
        }
    }

    /**
     * 校验资源是否合法
     *
     * 1. 校验相同父资源编号下，是否存在相同的资源名
     *
     * @param name 资源名字
     * @param pid 父资源编号
     * @param id 资源编号
     */
    private void checkResource(Integer pid, String name, Integer id) {
        ResourceDO resource = resourceMapper.selectByPidAndName(pid, name);
        if (resource == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资源
        if (id == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_NAME_DUPLICATE);
        }
        if (!resource.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.RESOURCE_NAME_DUPLICATE);
        }
    }

    /**
     * 初始化资源的通用属性。
     *
     * 例如说，只有菜单类型的资源，才设置 icon
     *
     * @param resource 资源
     */
    private void initResourceProperty(ResourceDO resource) {
        // 初始化资源为按钮类型时，无需 route 和 icon 属性
        if (ResourceTypeEnum.BUTTON.getType().equals(resource.getType())) {
            resource.setRoute(null);
            resource.setIcon(null);
        }
    }

}
