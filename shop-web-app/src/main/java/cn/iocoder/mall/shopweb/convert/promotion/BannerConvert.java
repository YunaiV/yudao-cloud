package cn.iocoder.mall.shopweb.convert.promotion;

import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.brand.BannerRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    List<BannerRespVO> convertList(List<BannerRespDTO> list);

}
