package cn.iocoder.mall.promotionservice.convert.activity;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PromotionActivityConvert {

    PromotionActivityConvert INSTANCE = Mappers.getMapper(PromotionActivityConvert.class);

    List<PromotionActivityRespDTO> convertList(List<PromotionActivityDO> list);

    @Mapping(source = "records", target = "list")
    PageResult<PromotionActivityRespDTO> convertPage(IPage<PromotionActivityDO> page);

}
