package cn.iocoder.mall.promotionservice.dal.mysql.mapper.banner;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerPageReqDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.banner.BannerDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerMapper extends BaseMapper<BannerDO> {

    default  List<BannerDO> selectList(BannerListReqDTO listReqDTO) {
        return selectList(new QueryWrapperX<BannerDO>().eqIfPresent("status", listReqDTO.getStatus()));
    }

    default IPage<BannerDO> selectPage(BannerPageReqDTO pageReqDTO) {
        return selectPage(new Page<>(pageReqDTO.getPageNo(), pageReqDTO.getPageSize()),
                new QueryWrapperX<BannerDO>().likeIfPresent("title", pageReqDTO.getTitle()));
    }

}
