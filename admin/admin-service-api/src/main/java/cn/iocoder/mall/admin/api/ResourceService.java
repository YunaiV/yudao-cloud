package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.ResourceBO;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    List<ResourceBO> getResourceByTypeAndRoleIds(Integer type, Set<Integer> roleIds);

}