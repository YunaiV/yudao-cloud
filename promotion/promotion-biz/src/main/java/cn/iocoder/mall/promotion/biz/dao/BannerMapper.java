package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerListDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * banner
 *
 * author: sin
 * time: 2020/5/14 14:19
 */
@Repository
@Mapper
public interface BannerMapper extends BaseMapper<BannerDO> {

    /**
     * 查询 - 列表
     *
     * @param dto
     * @return
     */
    // TODO FROM 芋艿 to 小范：Page 方法哈
    default IPage<BannerDO> selectBannerList(BannerListDTO dto) {
        LambdaQueryWrapper<BannerDO> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isEmpty(dto.getStatus())) {
            queryWrapper.eq(BannerDO::getStatus, dto.getStatus());
        }

        if (StringUtils.isEmpty(dto.getTitle())) {
            queryWrapper.like(BannerDO::getTitle, dto.getTitle());
        }

        queryWrapper.orderByDesc(BannerDO::getId);
        IPage<BannerDO> result = selectPage(new Page<>(), queryWrapper);
        return result;
    }

}
