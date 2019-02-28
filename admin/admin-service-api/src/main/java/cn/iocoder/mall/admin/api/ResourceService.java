package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.api.dto.ResourceAddDTO;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    List<ResourceBO> getResourceByTypeAndRoleIds(Integer type, Set<Integer> roleIds);

    CommonResult<ResourceBO> addResource(ResourceAddDTO resourceAddDTO);

}