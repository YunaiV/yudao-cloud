package cn.iocoder.mall.promotionservice.convert.coupon;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableRespDTO;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardRespDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponCardDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CouponCardConvert {

    CouponCardConvert INSTANCE = Mappers.getMapper(CouponCardConvert.class);

    CouponCardRespDTO convert(CouponCardDO bean);

    @Mapping(source = "records", target = "list")
    PageResult<CouponCardRespDTO> convertPage(IPage<CouponCardDO> page);

    @Named("convertCouponCardDOToCouponCardAvailableRespDTO") // 避免生成的方法名的冲突
    CouponCardAvailableRespDTO convert01(CouponCardDO bean);

}
