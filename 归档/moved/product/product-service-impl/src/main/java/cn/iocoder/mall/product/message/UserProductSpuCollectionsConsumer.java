package cn.iocoder.mall.product.message;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.api.UserProductSpuCollectionsService;
import cn.iocoder.mall.product.api.bo.UserProductSpuCollectionsBO;
import cn.iocoder.mall.product.api.dto.UserProductSpuCollectionsAddDTO;
import cn.iocoder.mall.product.api.dto.UserProductSpuCollectionsUpdateDTO;
import cn.iocoder.mall.product.api.message.ProductSpuCollectionMessage;
import cn.iocoder.mall.product.convert.UserProductSpuCollectionsConvert;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.biz.enums.UserErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 商品收藏 消费者
 * @author xiaofeng
 * @date 2019/07/02 19:57
 * @version 1.0
 */
@Service
@RocketMQMessageListener(topic = ProductSpuCollectionMessage.TOPIC, consumerGroup = "product-spu-consumer-group-"
        + ProductSpuCollectionMessage.TOPIC)
public class UserProductSpuCollectionsConsumer implements RocketMQListener<ProductSpuCollectionMessage> {

    @Autowired
    private UserProductSpuCollectionsService userProductSpuCollectionsService;

    @Reference(validation = "true", version = "${dubbo.consumer.UserService.version}")
    private UserService userService;

    @Override
    public void onMessage(ProductSpuCollectionMessage productSpuCollectionMessage) {
        UserBO userBO = userService.getUser(productSpuCollectionMessage.getUserId());
        if (userBO == null) {
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_NOT_EXISTS.getCode());
        }
        // 收藏
        if (productSpuCollectionMessage.getHasCollectionType().equals(1)) {
            this.saveUserProductSpuCollections(productSpuCollectionMessage, userBO.getNickname());
        } else if (productSpuCollectionMessage.getHasCollectionType().equals(2)) {
            // 取消收藏
            this.deleteUserProductSpuCollections(productSpuCollectionMessage.getUserId(),
                    productSpuCollectionMessage.getSpuId());
        }

    }

    /**
     * 保存商品收藏
     * @param productSpuCollectionMessage
     * @param nickname
     * @return
     */
    private int saveUserProductSpuCollections(final ProductSpuCollectionMessage productSpuCollectionMessage,
            final String nickname) {
        int result = 0;
        UserProductSpuCollectionsBO userProductSpuCollectionsBO = this.userProductSpuCollectionsService
                .getUserSpuCollectionsByUserIdAndSpuId(productSpuCollectionMessage.getUserId(),
                        productSpuCollectionMessage.getSpuId());
        if (userProductSpuCollectionsBO == null) {
            UserProductSpuCollectionsAddDTO userProductSpuCollectionsAddDTO = UserProductSpuCollectionsConvert.INSTANCE
                    .convert(productSpuCollectionMessage);
            userProductSpuCollectionsAddDTO.setNickname(StringUtils.isEmpty(nickname) ? "" : nickname);
            userProductSpuCollectionsAddDTO.setCreateTime(new Date());
            userProductSpuCollectionsAddDTO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
            result = userProductSpuCollectionsService.addUserSkuCollections(userProductSpuCollectionsAddDTO);
        } else {
            // 存在重新收藏
            if (userProductSpuCollectionsBO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
                UserProductSpuCollectionsUpdateDTO userProductSpuCollectionsUpdateDTO = this
                        .setUserProductSpuCollectionsUpdateDTO(userProductSpuCollectionsBO.getId(),
                                DeletedStatusEnum.DELETED_NO);
                result = this.userProductSpuCollectionsService
                        .updateUserProductSpuCollections(userProductSpuCollectionsUpdateDTO);
            }
        }
        return result;
    }

    /**
     * 取消收藏
     * @param userId
     * @param spuId
     * @return
     */
    private int deleteUserProductSpuCollections(final Integer userId, final Integer spuId) {
        UserProductSpuCollectionsBO userProductSpuCollectionsBO = this.userProductSpuCollectionsService
                .getUserSpuCollectionsByUserIdAndSpuId(userId, spuId);
        int result = 0;
        if (userProductSpuCollectionsBO != null) {
            // 未取消收藏的数据
            if (userProductSpuCollectionsBO.getDeleted().equals(DeletedStatusEnum.DELETED_NO.getValue())) {
                UserProductSpuCollectionsUpdateDTO userProductSpuCollectionsUpdateDTO = this
                        .setUserProductSpuCollectionsUpdateDTO(userProductSpuCollectionsBO.getId(),
                                DeletedStatusEnum.DELETED_YES);
                result = this.userProductSpuCollectionsService
                        .updateUserProductSpuCollections(userProductSpuCollectionsUpdateDTO);
            }
        }
        return result;
    }

    /**
     * 设置更新值
     * @param id
     * @param deletedStatusEnum
     * @return
     */
    private UserProductSpuCollectionsUpdateDTO setUserProductSpuCollectionsUpdateDTO(final Integer id,
                                                                                     final DeletedStatusEnum deletedStatusEnum) {
        return new UserProductSpuCollectionsUpdateDTO().setId(id).setUpdateTime(new Date())
                .setDeleted(deletedStatusEnum.getValue());
    }


}
