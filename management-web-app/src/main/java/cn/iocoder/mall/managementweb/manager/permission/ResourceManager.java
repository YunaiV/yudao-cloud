package cn.iocoder.mall.managementweb.manager.permission;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.managementweb.controller.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.managementweb.controller.permission.vo.ResourceVO;
import cn.iocoder.mall.managementweb.convert.permission.ResourceConvert;
import cn.iocoder.mall.systemservice.rpc.permission.ResourceRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 资源 Manager
*/
@Service
public class ResourceManager {

    @Reference(version = "${dubbo.consumer.ResourceRpc.version}", validation = "false")
    private ResourceRpc resourceRpc;

    /**
    * 创建资源
    *
    * @param createDTO 创建资源 DTO
    * @return 资源
    */
    public Integer createResource(ResourceCreateDTO createDTO, Integer createAdminId) {
        CommonResult<Integer> createResourceResult = resourceRpc.createResource(ResourceConvert.INSTANCE.convert(createDTO)
            .setCreateAdminId(createAdminId));
        createResourceResult.checkError();
        return createResourceResult.getData();
    }

    /**
    * 更新资源
    *
    * @param updateDTO 更新资源 DTO
    */
    public void updateResource(ResourceUpdateDTO updateDTO) {
        CommonResult<Boolean> updateResourceResult = resourceRpc.updateResource(ResourceConvert.INSTANCE.convert(updateDTO));
        updateResourceResult.checkError();
    }

    /**
    * 删除资源
    *
    * @param resourceId 资源编号
    */
    public void deleteResource(Integer resourceId) {
        CommonResult<Boolean> deleteResourceResult = resourceRpc.deleteResource(resourceId);
        deleteResourceResult.checkError();
    }

    /**
    * 获得资源
    *
    * @param resourceId 资源编号
    * @return 资源
    */
    public ResourceVO getResource(Integer resourceId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO> getResourceResult = resourceRpc.getResource(resourceId);
        getResourceResult.checkError();
        return ResourceConvert.INSTANCE.convert(getResourceResult.getData());
    }

    /**
    * 获得资源列表
    *
    * @param resourceIds 资源编号列表
    * @return 资源列表
    */
    public List<ResourceVO> listResource(List<Integer> resourceIds) {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO>> listResourceResult = resourceRpc.listResource(resourceIds);
        return ResourceConvert.INSTANCE.convertList(listResourceResult.getData());
    }

}
