package cn.iocoder.mall.search.biz.rpc.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.search.biz.api.user.ProductSearchRPC;
import cn.iocoder.mall.search.biz.service.ProductSearchService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service(validation = "true", version = "${dubbo.provider.ProductSearchRpc.version}")
public class ProductSearchRPCImpl implements ProductSearchRPC {

    @Autowired
    private ProductSearchService productSearchService;

    @Override
    public CommonResult<Integer> rebuild() {
        return null;
    }

    @Override
    public CommonResult<Boolean> save(Integer id){
//        ProductSpuDetailBO productSpuDetail = productSpuService.getProductSpuDetail(spuId);
//        return ProductSpuConvert.INSTANCE.convertDetail(productSpuDetail);
        return CommonResult.success(true);
    }


}
