package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.product.api.ProductSpuCollectionService;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.message.ProductSpuCollectionMessage;
import cn.iocoder.mall.product.dao.ProductSpuMapper;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProductSpuCollectionServiceImpl
 * @author xiaofeng
 * @date 2019/07/01 23:14
 * @version 1.0
 */
@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductSpuCollectionService.version}")
public class ProductSpuCollectionServiceImpl implements ProductSpuCollectionService {

    @Autowired
    private ProductSpuMapper productSpuMapper;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public boolean productSpuCollection(Integer spuId, Integer hasCollectionType, Integer userId) {
        ProductSpuDO productSpuDO = this.productSpuMapper.selectById(spuId);
        // 校验 Spu 是否存在
        if (productSpuDO == null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_SPU_NOT_EXISTS.getCode());
        }
        this.sendProductSpuCollectionMessage(productSpuDO, hasCollectionType, userId);
        return Boolean.TRUE;
    }

    /**
     * 发送商品收藏或取消消息
     * @param productSpuDO
     * @param hasCollectionType
     */
    // TODO FROM 芋艿 to ？？：切换到 Spring Cloud Stream 发送消息
    private void sendProductSpuCollectionMessage(final ProductSpuDO productSpuDO, final Integer hasCollectionType,
            final Integer userId) {
        List<String> result = Lists.newArrayList(Splitter.on(",").omitEmptyStrings().trimResults().split(productSpuDO.getPicUrls()));
        ProductSpuCollectionMessage productSpuCollectionMessage = new ProductSpuCollectionMessage()
                .setSpuId(productSpuDO.getId())
                .setSpuName(productSpuDO.getName())
                .setSpuImage(result.size() > 0 ? result.get(0) : "")
                .setSellPoint(productSpuDO.getSellPoint())
                .setPrice(productSpuDO.getPrice())
                .setHasCollectionType(hasCollectionType)
                .setUserId(userId);
        rocketMQTemplate.convertAndSend(ProductSpuCollectionMessage.TOPIC, productSpuCollectionMessage);
    }
}
