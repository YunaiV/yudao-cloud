package cn.iocoder.mall.product.biz.dao.product;

import cn.iocoder.mall.product.biz.dataobject.product.ProductBrandDO;
import cn.iocoder.mall.product.biz.dataobject.product.ProductCategoryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类数据持久层
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryDO> {

}
