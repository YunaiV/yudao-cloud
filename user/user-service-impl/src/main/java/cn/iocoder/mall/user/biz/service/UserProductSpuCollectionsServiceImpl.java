package cn.iocoder.mall.user.biz.service;

import cn.iocoder.mall.user.api.UserProductSpuCollectionsService;
import cn.iocoder.mall.user.api.bo.UserProductSpuCollectionsBO;
import cn.iocoder.mall.user.api.dto.UserProductSpuCollectionsAddDTO;
import cn.iocoder.mall.user.api.dto.UserProductSpuCollectionsUpdateDTO;
import cn.iocoder.mall.user.biz.convert.UserProductSpuCollectionsConvert;
import cn.iocoder.mall.user.biz.dao.UserProductSpuCollectionsMapper;
import cn.iocoder.mall.user.biz.dataobject.UserProductSpuCollectionsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserSkuCollectionsServiceImpl
 * @author xiaofeng
 * @date 2019/07/01 21:02
 * @version 1.0
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.UserProductSpuCollectionsService.version}")
public class UserProductSpuCollectionsServiceImpl implements UserProductSpuCollectionsService {


    @Autowired
    private UserProductSpuCollectionsMapper userProductSpuCollectionsMapper;


    @Override
    public int addUserSkuCollections(UserProductSpuCollectionsAddDTO userProductSpuCollectionsAddDTO) {
        return userProductSpuCollectionsMapper
                .insert(UserProductSpuCollectionsConvert.INSTANCE.convert(userProductSpuCollectionsAddDTO));
    }

    @Override
    public UserProductSpuCollectionsBO getUserSpuCollectionsByUserIdAndSpuId(Integer userId, Integer spuId) {
        UserProductSpuCollectionsDO userProductSpuCollectionsDO = userProductSpuCollectionsMapper
                .getUserSpuCollectionsByUserIdAndSpuId(userId, spuId);
        return UserProductSpuCollectionsConvert.INSTANCE.convert(userProductSpuCollectionsDO);
    }

    @Override
    public int updateUserProductSpuCollections(UserProductSpuCollectionsUpdateDTO userProductSpuCollectionsUpdateDTO) {
        return userProductSpuCollectionsMapper
                .updateById(UserProductSpuCollectionsConvert.INSTANCE.convert(userProductSpuCollectionsUpdateDTO));
    }


}
