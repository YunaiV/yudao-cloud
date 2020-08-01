package cn.iocoder.mall.search.biz.service;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.search.biz.dao.ProductRepository;
import cn.iocoder.mall.search.biz.dto.ProductSearchPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductSearchService.version}")
public class ProductSearchServiceImpl implements ProductSearchService {

    private static final Integer REBUILD_FETCH_PER_SIZE = 100;

    @Autowired
    private ProductRepository productRepository;


//    @Reference(validation = "true", version = "${dubbo.consumer.ProductSpuService.version}")
//    private ProductSpuService productSpuService;
//    @Reference(validation = "true", version = "${dubbo.consumer.ProductCategoryService.version}")
//    private ProductCategoryService productCategoryService;
//    @Reference(validation = "true", version = "${dubbo.consumer.CartService.version}")
//    private CartService cartService;

//    @Override
//    public Integer rebuild() {
//        // TODO 芋艿，因为目前商品比较少，所以写的很粗暴。等未来重构
//        Integer lastId = null;
//        int rebuildCounts = 0;
//        while (true) {
//            List<ProductSpuDetailBO> spus = productSpuService.getProductSpuDetailListForSync(lastId, REBUILD_FETCH_PER_SIZE);
//            rebuildCounts += spus.size();
//            // 存储到 ES 中
//            List<ESProductDO> products = spus.stream().map(this::convert).collect(Collectors.toList());
//            productRepository.saveAll(products);
//            // 设置新的 lastId ，或者结束
//            if (spus.size() < REBUILD_FETCH_PER_SIZE) {
//                break;
//            } else {
//                lastId = spus.get(spus.size() - 1).getId();
//            }
//        }
//        // 返回成功
//        return rebuildCounts;
//    }
//

}
