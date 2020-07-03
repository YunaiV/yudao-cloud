package cn.iocoder.mall.demo.business.service;

import cn.iocoder.mall.demo.business.api.DemoUserService;
import cn.iocoder.mall.demo.business.bo.user.DemoUserBO;
import cn.iocoder.mall.demo.business.cacheobject.user.DemoUserCacheObject;
import cn.iocoder.mall.demo.business.convert.DemoUserConvert;
import cn.iocoder.mall.demo.business.dao.redis.DemoUserCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoUserServiceImpl implements DemoUserService {

    @Autowired
    private DemoUserCacheDao userCacheDao;

    @Override
    public DemoUserBO get(Integer id) {
        DemoUserCacheObject userCacheObject = userCacheDao.get(id);
        if (userCacheObject == null) { // TODO 芋艿，临时
            userCacheDao.set(id, new DemoUserCacheObject().setId(id)
                .setName("芋艿").setGender(1));
        }
        return DemoUserConvert.INSTANCE.convert(userCacheObject);
    }

}
