package cn.iocoder.mall.search.biz.dao;

import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

    @Deprecated
    ESProductDO findByName(String name);

}
