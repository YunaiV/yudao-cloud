package cn.iocoder.mall.product.service;

import cn.iocoder.mall.product.bo.ProductSpuBO;
import cn.iocoder.mall.product.convert.ProductSpuConvert;
import cn.iocoder.mall.product.dao.ProductSpuMapper;
import cn.iocoder.mall.product.dataobject.ProductSpuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 实际上不用添加。添加的原因是，必须 Spring 报错提示
@com.alibaba.dubbo.config.annotation.Service
public class ProductSpuService implements cn.iocoder.mall.product.service.api.ProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuDAO;

    public ProductSpuBO getProductSpu(Integer id) {
        ProductSpuDO productSpuDO = productSpuDAO.selectById(id);
        // 转换成 BO
        return ProductSpuConvert.INSTANCE.convert(productSpuDO);
    }

}