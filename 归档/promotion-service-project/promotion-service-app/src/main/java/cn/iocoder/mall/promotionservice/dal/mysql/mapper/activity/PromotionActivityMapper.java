package cn.iocoder.mall.promotionservice.dal.mysql.mapper.activity;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PromotionActivityMapper extends BaseMapper<PromotionActivityDO> {

    default List<PromotionActivityDO> selectListByStatus(Collection<Integer> statuses) {
        return selectList(new QueryWrapper<PromotionActivityDO>().in("status", statuses));
    }

    default IPage<PromotionActivityDO> selectPage(PromotionActivityPageReqDTO pageReqDTO) {
        return selectPage(new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize()),
                new QueryWrapperX<PromotionActivityDO>().likeIfPresent("title", pageReqDTO.getTitle())
                        .eqIfPresent("activity_type", pageReqDTO.getActivityType())
                        .inIfPresent("status", pageReqDTO.getStatuses()));
    }

    default List<PromotionActivityDO> selectList(PromotionActivityListReqDTO listReqDTO) {
        return selectList(new QueryWrapperX<PromotionActivityDO>().inIfPresent("id", listReqDTO.getActiveIds()));
    }

}
