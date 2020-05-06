package cn.iocoder.mall.product.biz.service.product;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.attr.ProductAttrBO;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrBO2;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrSimpleBO;
import cn.iocoder.mall.product.biz.bo.product.ProductAttrValueBO;
import cn.iocoder.mall.product.biz.dto.attr.AdminProductAttrPageDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrAddDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrUpdateDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrValueAddDTO;
import cn.iocoder.mall.product.biz.dto.product.ProductAttrValueUpdateDTO;

import java.util.List;

public interface ProductAttrService {
    /**
     * 获取规格分页数据
     *
     * @param productAttrPageDTO 查询参数
     * @return 规格分页信息
     */
    PageResult<ProductAttrBO> getProductAttrPage(AdminProductAttrPageDTO productAttrPageDTO);

    /**
     * 获得规格属性数组
     * <p>
     * 注意，该方法过滤了禁用的规格
     *
     * @return 规格属性数组
     */
    List<ProductAttrSimpleBO> getProductAttrList();

    ProductAttrBO2 addProductAttr(Integer adminId, ProductAttrAddDTO productAttrAddDTO);

    Boolean updateProductAttr(Integer adminId, ProductAttrUpdateDTO productAttrUpdateDTO);

    Boolean updateProductAttrStatus(Integer adminId, Integer productAttrId,
                                    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    ProductAttrValueBO addProductAttrValue(Integer adminId, ProductAttrValueAddDTO productAttrValueAddDTO);

    Boolean updateProductAttrValue(Integer adminId, ProductAttrValueUpdateDTO productAttrValueUpdateDTO);

    Boolean updateProductAttrValueStatus(Integer adminId, Integer productAttrValueId,
                                         @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

}
