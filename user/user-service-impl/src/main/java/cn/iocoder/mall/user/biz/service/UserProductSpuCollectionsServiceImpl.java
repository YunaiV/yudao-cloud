package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserProductSpuCollectionsService;
import cn.iocoder.mall.user.api.bo.UserProductSpuCollectionsBO;
import cn.iocoder.mall.user.api.bo.UserProductSpuCollectionsPageBO;
import cn.iocoder.mall.user.api.dto.UserProductSpuCollectionsAddDTO;
import cn.iocoder.mall.user.api.dto.UserProductSpuCollectionsPageDTO;
import cn.iocoder.mall.user.api.dto.UserProductSpuCollectionsUpdateDTO;
import cn.iocoder.mall.user.biz.convert.UserProductSpuCollectionsConvert;
import cn.iocoder.mall.user.biz.dao.UserProductSpuCollectionsMapper;
import cn.iocoder.mall.user.biz.dataobject.UserProductSpuCollectionsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @Override
    public CommonResult<UserProductSpuCollectionsPageBO> getUserProductSpuCollectionsPage(
            UserProductSpuCollectionsPageDTO userProductSpuCollectionsPageDTO) {
        final int offset =
                (userProductSpuCollectionsPageDTO.getPageNo() - 1) * userProductSpuCollectionsPageDTO.getPageSize();
        final int totalCount = this.userProductSpuCollectionsMapper
                .selectCountByUser(userProductSpuCollectionsPageDTO.getUserId());
        if (totalCount == 0) {
            return CommonResult
                    .success(new UserProductSpuCollectionsPageBO().setList(Collections.emptyList()).setTotal(0));
        }

        List<UserProductSpuCollectionsDO> list = userProductSpuCollectionsMapper
                .selectListByUser(userProductSpuCollectionsPageDTO.getUserId(), offset,
                        userProductSpuCollectionsPageDTO.getPageSize());
        if (CollectionUtils.isEmpty(list)) {
            return CommonResult.success(
                    new UserProductSpuCollectionsPageBO().setList(Collections.emptyList()).setTotal(totalCount));
        }

        UserProductSpuCollectionsPageBO userProductSpuCollectionsPageBO = new UserProductSpuCollectionsPageBO();
        userProductSpuCollectionsPageBO.setList(UserProductSpuCollectionsConvert.INSTANCE.convert(list));
        // 查询分页总数
        userProductSpuCollectionsPageBO.setTotal(totalCount);
        // 返回结果
        return CommonResult.success(userProductSpuCollectionsPageBO);
    }


    /**
     * 取消收藏
     * @param userId
     * @param spuId
     * @return
     */
    @Override
    public CommonResult<Boolean> deleteUserProductSpuCollections(final Integer userId, final Integer spuId) {
        UserProductSpuCollectionsBO userProductSpuCollectionsBO = this
                .getUserSpuCollectionsByUserIdAndSpuId(userId, spuId);
        int result = 0;
        if (userProductSpuCollectionsBO != null) {
            // 未取消收藏的数据
            if (userProductSpuCollectionsBO.getDeleted().equals(DeletedStatusEnum.DELETED_NO.getValue())) {
                UserProductSpuCollectionsUpdateDTO userProductSpuCollectionsUpdateDTO = new UserProductSpuCollectionsUpdateDTO()
                        .setId(userProductSpuCollectionsBO.getId()).setUpdateTime(new Date())
                        .setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
                result = this.updateUserProductSpuCollections(userProductSpuCollectionsUpdateDTO);
            }
        }
        return CommonResult.success(result > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Override
    public CommonResult<Boolean> hasUserSpuFavorite(final Integer spuId, final Integer userId) {
        UserProductSpuCollectionsBO userProductSpuCollectionsBO = this
                .getUserSpuCollectionsByUserIdAndSpuId(userId, spuId);

        if (userProductSpuCollectionsBO != null) {
            // 收藏
            final boolean hasCollect = userProductSpuCollectionsBO.getDeleted()
                    .equals(DeletedStatusEnum.DELETED_NO.getValue());
            return CommonResult.success(hasCollect);
        }
        return CommonResult.success(Boolean.FALSE);
    }
}
