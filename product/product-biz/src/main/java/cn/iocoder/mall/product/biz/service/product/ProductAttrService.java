package cn.iocoder.mall.product.biz.service.product;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrSimpleWithValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrWithValueBO;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.biz.dto.attr.*;

import java.util.List;
import java.util.Set;

public interface ProductAttrService {
    /**
     * 获取规格分页数据
     *
     * @param productAttrPageDTO 查询参数
     * @return 规格分页信息
     */
    PageResult<ProductAttrWithValueBO> getProductAttrPage(AdminProductAttrPageDTO productAttrPageDTO);

    /**
     * 获得规格属性数组
     * <p>
     * 注意，该方法过滤了禁用的规格
     *
     * @return 规格属性数组
     */
    List<ProductAttrSimpleWithValueBO> getProductAttrList();

    /**
     * 添加商品规格
     *
     * @param adminId           操作人ID
     * @param productAttrAddDTO 添加参数
     * @return 添加的规格
     */
    ProductAttrBO addProductAttr(Integer adminId, ProductAttrAddDTO productAttrAddDTO);

    /**
     * 更新规格
     *
     * @param adminId              操作人
     * @param productAttrUpdateDTO 更新规格
     * @return 成功标识
     */
    Boolean updateProductAttr(Integer adminId, ProductAttrUpdateDTO productAttrUpdateDTO);

    /**
     * 更新规格状态
     *
     * @param adminId       操作人
     * @param productAttrId 规格ID
     * @param status        状态
     * @return 成功标识
     */
    Boolean updateProductAttrStatus(Integer adminId, Integer productAttrId, Integer status);

    ProductAttrValueBO addProductAttrValue(Integer adminId, ProductAttrValueAddDTO productAttrValueAddDTO);

    Boolean updateProductAttrValue(Integer adminId, ProductAttrValueUpdateDTO productAttrValueUpdateDTO);

    Boolean updateProductAttrValueStatus(Integer adminId, Integer productAttrValueId,
                                         @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    List<ProductAttrAndValuePairBO> validProductAttrAndValue(Set<Integer> productAttrValueIds, boolean validStatus);
}
