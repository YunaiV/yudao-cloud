package cn.iocoder.mall.promotionservice.convert.banner;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerCreateReqDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerUpdateReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.banner.BannerDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    BannerDO convert(BannerCreateReqDTO bean);

    BannerDO convert(BannerUpdateReqDTO bean);

    @Mapping(source = "records", target = "list")
    PageResult<BannerRespDTO> convertPage(IPage<BannerDO> page);

    List<BannerRespDTO> convertList(List<BannerDO> list);

}
