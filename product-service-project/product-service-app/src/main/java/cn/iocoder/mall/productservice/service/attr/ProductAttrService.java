package cn.iocoder.mall.productservice.service.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.attr.ProductAttrConvert;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrKeyDO;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.productservice.dal.mysql.mapper.attr.ProductAttrKeyMapper;
import cn.iocoder.mall.productservice.dal.mysql.mapper.attr.ProductAttrValueMapper;
import cn.iocoder.mall.productservice.service.attr.bo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.*;

/**
 * 商品规格 Service
 *
 * 包括商品键和值
 */
@Service
@Valid
public class ProductAttrService {

    @Autowired
    private ProductAttrKeyMapper productAttrKeyMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    public List<ProductAttrKeyValueBO> validProductAttr(Set<Integer> productAttrValueIds, boolean validStatus) {
        // 首先，校验规格 Value
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectBatchIds(productAttrValueIds);
        if (attrValues.size() != productAttrValueIds.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_NOT_EXIST);
        }
        if (validStatus) {
            for (ProductAttrValueDO attrValue : attrValues) { // 同时，校验下状态
                if (CommonStatusEnum.DISABLE.getValue().equals(attrValue.getStatus())) {
                    throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_NOT_EXIST);
                }
            }
        }
        // 然后，校验规 Key
        Set<Integer> attrKeyIds = CollectionUtils.convertSet(attrValues, ProductAttrValueDO::getAttrId);
        List<ProductAttrKeyDO> attrKeys = productAttrKeyMapper.selectBatchIds(attrKeyIds);
        if (attrKeys.size() != attrKeyIds.size()) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        if (validStatus) {
            for (ProductAttrKeyDO attrKey : attrKeys) { // 同时，校验下状态
                if (CommonStatusEnum.DISABLE.getValue().equals(attrKey.getStatus())) {
                    throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
                }
            }
        }
        // 返回成功
        Map<Integer, ProductAttrKeyDO> attrKeyMap = CollectionUtils.convertMap(attrKeys,
                ProductAttrKeyDO::getId, attrKeyDO -> attrKeyDO); // ProductAttrDO 的映射，方便查找。
        return attrValues.stream().map(attrValueDO -> new ProductAttrKeyValueBO()
                .setAttrKeyId(attrValueDO.getAttrId()).setAttrKeyName(attrKeyMap.get(attrValueDO.getAttrId()).getName())
                .setAttrValueId(attrValueDO.getId()).setAttrValueName(attrValueDO.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 创建商品规格键
     *
     * @param createBO 创建商品规格键 BO
     * @return 商品规格键
     */
    public ProductAttrKeyBO createProductAttrKey(@Valid ProductAttrKeyCreateBO createBO) {
        // 校验规格键的名字是否重复
        if (productAttrKeyMapper.selectByName(createBO.getName()) != null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_EXISTS);
        }
        // 插入到数据库
        ProductAttrKeyDO productAttrKeyDO = ProductAttrConvert.INSTANCE.convert(createBO);
        productAttrKeyMapper.insert(productAttrKeyDO);
        // 返回
        return ProductAttrConvert.INSTANCE.convert(productAttrKeyDO);
    }

    /**
     * 更新商品规格键
     *
     * @param updateBO 更新商品规格键 BO
     */
    public void updateProductAttrKey(@Valid ProductAttrKeyUpdateBO updateBO) {
        // 校验规格键的名字是否重复
        ProductAttrKeyDO attrKeyDOByName = productAttrKeyMapper.selectByName(updateBO.getName());
        if (attrKeyDOByName != null && !attrKeyDOByName.getId().equals(updateBO.getId())) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_EXISTS);
        }
        // 校验更新的商品规格键是否存在
        if (productAttrKeyMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        // 更新到数据库
        ProductAttrKeyDO updateObject = ProductAttrConvert.INSTANCE.convert(updateBO);
        productAttrKeyMapper.updateById(updateObject);
    }

    /**
     * 删除商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     */
    public void deleteProductAttrKey(Integer productAttrKeyId) {
        // 校验删除的商品规格键是否存在
        if (productAttrKeyMapper.selectById(productAttrKeyId) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        // 标记删除
        productAttrKeyMapper.deleteById(productAttrKeyId);
    }

    /**
     * 获得商品规格键
     *
     * @param productAttrKeyId 商品规格键编号
     * @return 商品规格键
     */
    public ProductAttrKeyBO getProductAttrKey(Integer productAttrKeyId) {
        ProductAttrKeyDO productAttrKeyDO = productAttrKeyMapper.selectById(productAttrKeyId);
        return ProductAttrConvert.INSTANCE.convert(productAttrKeyDO);
    }

    /**
     * 获得商品规格键列表
     *
     * @param productAttrKeyIds 商品规格键编号列表
     * @return 商品规格键列表
     */
    public List<ProductAttrKeyBO> listProductAttrKeys(List<Integer> productAttrKeyIds) {
        List<ProductAttrKeyDO> productAttrKeyDOs = productAttrKeyMapper.selectBatchIds(productAttrKeyIds);
        return ProductAttrConvert.INSTANCE.convertList(productAttrKeyDOs);
    }

    /**
     * 获得商品规格键分页
     *
     * @param pageBO 商品规格键分页查询
     * @return 商品规格键分页结果
     */
    public PageResult<ProductAttrKeyBO> pageProductAttrKey(ProductAttrKeyPageBO pageBO) {
        IPage<ProductAttrKeyDO> productAttrKeyDOPage = productAttrKeyMapper.selectPage(pageBO);
        return ProductAttrConvert.INSTANCE.convertPage(productAttrKeyDOPage);
    }

}
