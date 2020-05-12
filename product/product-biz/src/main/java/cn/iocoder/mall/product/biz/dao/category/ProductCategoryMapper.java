package cn.iocoder.mall.product.biz.dao.category;

import cn.iocoder.mall.product.biz.dataobject.category.ProductCategoryDO;
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
