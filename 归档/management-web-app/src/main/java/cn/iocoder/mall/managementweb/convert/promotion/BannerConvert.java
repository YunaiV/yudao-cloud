package cn.iocoder.mall.managementweb.convert.promotion;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerPageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerRespVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerUpdateReqVO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerUpdateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    BannerCreateReqDTO convert(BannerCreateReqVO bean);

    BannerUpdateReqDTO convert(BannerUpdateReqVO bean);

    BannerPageReqDTO convert(BannerPageReqVO bean);

    PageResult<BannerRespVO> convertPage(PageResult<BannerRespDTO> page);

}
