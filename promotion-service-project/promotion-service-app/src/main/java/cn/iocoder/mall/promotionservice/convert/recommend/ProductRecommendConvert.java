package cn.iocoder.mall.promotionservice.convert.recommend;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendRespDTO;
import cn.iocoder.mall.promotion.api.rpc.recommend.dto.ProductRecommendUpdateReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.recommend.ProductRecommendDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductRecommendConvert {

    ProductRecommendConvert INSTANCE = Mappers.getMapper(ProductRecommendConvert.class);

    List<ProductRecommendRespDTO> convertList(List<ProductRecommendDO> list);

    @Mapping(source = "records", target = "list")
    PageResult<ProductRecommendRespDTO> convertPage(IPage<ProductRecommendDO> page);

    ProductRecommendDO convert(ProductRecommendCreateReqDTO bean);

    ProductRecommendDO convert(ProductRecommendUpdateReqDTO bean);

}
