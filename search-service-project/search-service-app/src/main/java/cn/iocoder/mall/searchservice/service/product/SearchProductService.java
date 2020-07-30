package cn.iocoder.mall.searchservice.service.product;

import cn.iocoder.mall.searchservice.convert.product.SearchProductConvert;
import cn.iocoder.mall.searchservice.dal.es.dataobject.ESProductDO;
import cn.iocoder.mall.searchservice.dal.es.repository.ESProductRepository;
import cn.iocoder.mall.searchservice.service.product.bo.SearchProductCreateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchProductService {

    @Autowired
    private ESProductRepository productRepository;

    public void createSearchProduct(SearchProductCreateBO createBO) {
        ESProductDO productDO = SearchProductConvert.INSTANCE.convert(createBO);
        productRepository.save(productDO);
    }

}
