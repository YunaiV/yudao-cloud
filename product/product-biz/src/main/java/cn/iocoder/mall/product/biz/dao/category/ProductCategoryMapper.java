package cn.iocoder.mall.product.biz.dao.category;

import cn.iocoder.mall.product.biz.dataobject.category.ProductCategoryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Repository;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类数据持久层
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryDO> {
    /**
     * 查询商品分类的下一级子分类数量
     * @param productCategoryId
     * @return
     */
    default Integer selectChildCategoryCount(Integer productCategoryId) {
        return this.selectCount(
                Wrappers.<ProductCategoryDO>lambdaQuery().eq(ProductCategoryDO::getPid, productCategoryId)
        );
    }
}
