package cn.iocoder.mall.productservice.convert.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrKeyDO;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import cn.iocoder.mall.productservice.rpc.attr.dto.*;
import cn.iocoder.mall.productservice.service.attr.bo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    ProductAttrKeyDO convert(ProductAttrKeyCreateBO bean);

    ProductAttrKeyBO convert(ProductAttrKeyDO bean);

    ProductAttrKeyDO convert(ProductAttrKeyUpdateBO bean);

    List<ProductAttrKeyBO> convertList(List<ProductAttrKeyDO> list);

    @Mapping(source = "records", target = "list")
    PageResult<ProductAttrKeyBO> convertPage(IPage<ProductAttrKeyDO> page);

    ProductAttrKeyCreateBO convert(ProductAttrKeyCreateReqDTO bean);

    ProductAttrKeyUpdateBO convert(ProductAttrKeyUpdateReqDTO bean);

    ProductAttrKeyRespDTO convert(ProductAttrKeyBO bean);

    List<ProductAttrKeyRespDTO> convertList02(List<ProductAttrKeyBO> list);

    ProductAttrKeyPageBO convert(ProductAttrKeyPageReqDTO bean);

    PageResult<ProductAttrKeyRespDTO> convertPage(PageResult<ProductAttrKeyBO> page);

    ProductAttrValueDO convert(ProductAttrValueCreateBO bean);

    ProductAttrValueBO convert(ProductAttrValueDO bean);

    ProductAttrValueDO convert(ProductAttrValueUpdateBO bean);

    List<ProductAttrValueBO> convertList03(List<ProductAttrValueDO> list);

    ProductAttrValueCreateBO convert(ProductAttrValueCreateReqDTO bean);

    ProductAttrValueUpdateBO convert(ProductAttrValueUpdateReqDTO bean);

    ProductAttrValueRespDTO convert(ProductAttrValueBO bean);

    List<ProductAttrValueRespDTO> convertList04(List<ProductAttrValueBO> list);

    ProductAttrValueListQueryBO convert(ProductAttrValueListQueryReqDTO bean);

}
