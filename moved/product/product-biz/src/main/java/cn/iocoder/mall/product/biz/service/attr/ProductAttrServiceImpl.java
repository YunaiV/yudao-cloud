package cn.iocoder.mall.product.biz.service.attr;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrWithValueBO;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.biz.convert.attr.ProductAttrConvert;
import cn.iocoder.mall.product.biz.dao.attr.ProductAttrMapper;
import cn.iocoder.mall.product.biz.dao.attr.ProductAttrValueMapper;
import cn.iocoder.mall.product.biz.dataobject.attr.ProductAttrDO;
import cn.iocoder.mall.product.biz.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.product.biz.dto.attr.*;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.enums.attr.ProductAttrConstants;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品规格 Service 实现类
 *
 * @see ProductAttrDO
 * @see ProductAttrValueDO
 */
@Service
public class ProductAttrServiceImpl implements ProductAttrService {

    @Override
    public List<ProductAttrSimpleWithValueBO> getProductAttrList() {
        // 查询所有开启的规格数组
        List<ProductAttrDO> attrDos = productAttrMapper.selectList(Wrappers.<ProductAttrDO>query().lambda()
                .in(ProductAttrDO::getStatus, ProductAttrConstants.ATTR_STATUS_ENABLE)
                .eq(ProductAttrDO::getDeleted, false));
        // 如果为空，则返回空
        if (attrDos.isEmpty()) {
            return Collections.emptyList();
        }
        List<ProductAttrSimpleWithValueBO> attrs = ProductAttrConvert.INSTANCE.convertAttrSimple(attrDos);
        // 将规格值拼接上去
        List<ProductAttrValueDO> attrValues = productAttrValueMapper.selectList(Wrappers.<ProductAttrValueDO>query().lambda()
                .in(ProductAttrValueDO::getStatus, ProductAttrConstants.ATTR_STATUS_ENABLE)
                .eq(ProductAttrValueDO::getDeleted, false));
        Map<Integer, List<ProductAttrValueDO>> attrValueMap = attrValues.stream().collect(Collectors.groupingBy(ProductAttrValueDO::getAttrId));
        for (ProductAttrSimpleWithValueBO item : attrs) {
            item.setValues(ProductAttrConvert.INSTANCE.convertAttrValueSimple(attrValueMap.get(item.getId())));
        }
        return attrs;
    }

}
