package cn.iocoder.mall.promotionservice.dal.mysql.mapper.coupon;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardPageReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.coupon.CouponCardDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCardMapper extends BaseMapper<CouponCardDO> {

    default List<CouponCardDO> selectListByUserIdAndStatus(Integer userId, Integer status) {
        return selectList(new QueryWrapper<CouponCardDO>().eq("user_id", userId)
                .eq("status", status));
    }

    default int selectCountByUserIdAndTemplateId(Integer userId, Integer templateId) {
        return selectCount(new QueryWrapper<CouponCardDO>().eq("user_id", userId)
                .eq("template_id", templateId));
    }

    default int updateByIdAndStatus(Integer id, Integer status, CouponCardDO updateObj) {
        return update(updateObj, new QueryWrapper<CouponCardDO>().eq("id", id)
                .eq("status", status));
    }

    default IPage<CouponCardDO> selectPage(CouponCardPageReqDTO pageReqDTO) {
        return selectPage(new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize()),
                new QueryWrapperX<CouponCardDO>().eqIfPresent("user_id", pageReqDTO.getUserId())
                    .eqIfPresent("status", pageReqDTO.getStatus()));
    }

}
