package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.api.ProductAttrService;
import cn.iocoder.mall.product.api.bo.*;
import cn.iocoder.mall.product.api.constant.ProductAttrConstants;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.dto.*;
import cn.iocoder.mall.product.convert.ProductAttrConvert;
import cn.iocoder.mall.product.dao.ProductAttrMapper;
import cn.iocoder.mall.product.dao.ProductAttrValueMapper;
import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品规格 Service 实现类
 *
 * @see cn.iocoder.mall.product.dataobject.ProductAttrDO
 * @see cn.iocoder.mall.product.dataobject.ProductAttrValueDO
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductAttrService.version}")
public class ProductAttrServiceImpl implements ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Override
    public List<ProductAttrSimpleBO> getProductAttrList() {
        // 查询所有开启的规格数组
        List<ProductAttrSimpleBO> attrs = ProductAttrConvert.INSTANCE.convert3(productAttrMapper.selectListByStatus(ProductAttrConstants.ATTR_STATUS_ENABLE));
        // 如果为空，则返回空
        if (attrs.isEmpty()) {
            return Collections.emptyList();
        }
        // 将规格值拼接上去
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectListByStatus(ProductAttrConstants.ATTR_VALUE_STATUS_ENABLE);
        ImmutableListMultimap<Integer, ProductAttrValueDO> attrValueMap = Multimaps.index(attrValues, ProductAttrValueDO::getAttrId); // KEY 是 attrId ，VALUE 是 ProductAttrValueDO 数组
        for (ProductAttrSimpleBO productAttrSimpleBO : attrs) {
            productAttrSimpleBO.setValues(ProductAttrConvert.INSTANCE.convert4(((attrValueMap).get(productAttrSimpleBO.getId()))));
        }
        return attrs;
    }


}
