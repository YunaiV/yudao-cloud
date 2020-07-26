package cn.iocoder.mall.productservice.convert.spu;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSpuDO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuPageReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuUpdateReqDTO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuCreateBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuPageBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuDO convert(ProductSpuCreateBO bean);

    ProductSpuBO convert(ProductSpuDO bean);

    ProductSpuDO convert(ProductSpuUpdateBO bean);

    List<ProductSpuBO> convertList(List<ProductSpuDO> list);

	PageResult<ProductSpuBO> convertPage(IPage<ProductSpuDO> page);

    ProductSpuCreateBO convert(ProductSpuCreateReqDTO bean);

    ProductSpuUpdateBO convert(ProductSpuUpdateReqDTO bean);

    ProductSpuRespDTO convert(ProductSpuBO bean);

    List<ProductSpuRespDTO> convertList02(List<ProductSpuBO> list);

    ProductSpuPageBO convert(ProductSpuPageReqDTO bean);

    PageResult<ProductSpuRespDTO> convertPage(PageResult<ProductSpuBO> page);

}
