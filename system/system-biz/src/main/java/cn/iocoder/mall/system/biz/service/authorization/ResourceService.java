package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;

import java.util.Collection;
import java.util.List;

public interface ResourceService {

    List<ResourceBO> getListByPermissions(Collection<String> permissions);

}
