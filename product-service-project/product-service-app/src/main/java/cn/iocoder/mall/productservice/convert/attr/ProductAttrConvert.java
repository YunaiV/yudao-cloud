package cn.iocoder.mall.productservice.convert.attr;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrKeyDO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyPageReqDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyRespDTO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyUpdateReqDTO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyBO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyCreateBO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyPageBO;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    ProductAttrKeyDO convert(ProductAttrKeyCreateBO bean);

    ProductAttrKeyBO convert(ProductAttrKeyDO bean);

    ProductAttrKeyDO convert(ProductAttrKeyUpdateBO bean);

    List<ProductAttrKeyBO> convertList(List<ProductAttrKeyDO> list);

	PageResult<ProductAttrKeyBO> convertPage(IPage<ProductAttrKeyDO> page);

    ProductAttrKeyCreateBO convert(ProductAttrKeyCreateReqDTO bean);

    ProductAttrKeyUpdateBO convert(ProductAttrKeyUpdateReqDTO bean);

    ProductAttrKeyRespDTO convert(ProductAttrKeyBO bean);

    List<ProductAttrKeyRespDTO> convertList02(List<ProductAttrKeyBO> list);

    ProductAttrKeyPageBO convert(ProductAttrKeyPageReqDTO bean);

    PageResult<ProductAttrKeyRespDTO> convertPage(PageResult<ProductAttrKeyBO> page);

}
