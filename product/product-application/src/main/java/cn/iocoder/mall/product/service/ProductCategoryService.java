package cn.iocoder.mall.product.service;

import cn.iocoder.mall.product.bo.ProductCategoryBO;
import cn.iocoder.mall.product.convert.ProductCategoryConvert;
import cn.iocoder.mall.product.dao.ProductCategoryMapper;
import cn.iocoder.mall.product.dataobject.ProductCategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
//@com.alibaba.dubbo.config.annotation.Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategoryBO> getListByPid(Integer pid) {
        List<ProductCategoryDO> categoryList = productCategoryMapper.selectListByPidAndStatusOrderBySort(pid, ProductCategoryDO.STATUS_ENABLE);
        return ProductCategoryConvert.INSTANCE.convertToBO(categoryList);
    }

}