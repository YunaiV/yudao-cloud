package cn.iocoder.mall.promotionservice.service.banner;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.*;
import cn.iocoder.mall.promotionservice.convert.banner.BannerConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.banner.BannerDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.banner.BannerMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants.BANNER_NOT_EXISTS;


@Service
@Validated
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 获得 Banner 列表
     *
     * @param listReqDTO Banner 列表查询
     * @return Banner 列表
     */
    public List<BannerRespDTO> listBanners(BannerListReqDTO listReqDTO) {
        List<BannerDO> banners = bannerMapper.selectList(listReqDTO);
        return BannerConvert.INSTANCE.convertList(banners);
    }

    /**
     * 获得 Banner 分页
     *
     * @param bannerPageDTO Banner 分页查询
     * @return Banner 分页结果
     */
    public PageResult<BannerRespDTO> pageBanner(BannerPageReqDTO bannerPageDTO) {
        IPage<BannerDO> bannerPage = bannerMapper.selectPage(bannerPageDTO);
        return BannerConvert.INSTANCE.convertPage(bannerPage);
    }

    /**
     * 创建 Banner
     *
     * @param createReqDTO 创建 Banner 信息
     * @return banner
     */
    public Integer createBanner(@Valid BannerCreateReqDTO createReqDTO) {
        // 插入到数据库
        BannerDO bannerDO = BannerConvert.INSTANCE.convert(createReqDTO);
        bannerMapper.insert(bannerDO);
        // 返回
        return bannerDO.getId();
    }

    /**
     * 更新 Banner
     *
     * @param updateReqDTO 更新 Banner 信息
     */
    public void updateBanner(@Valid BannerUpdateReqDTO updateReqDTO) {
        // 校验更新的 Banner 是否存在
        if (bannerMapper.selectById(updateReqDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(BANNER_NOT_EXISTS);
        }
        // 更新到数据库
        BannerDO updateObject = BannerConvert.INSTANCE.convert(updateReqDTO);
        bannerMapper.updateById(updateObject);
    }

    /**
     * 删除 Banner
     *
     * @param bannerId Banner 编号
     */
    public void deleteBanner(Integer bannerId) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerId) == null) {
            throw ServiceExceptionUtil.exception(BANNER_NOT_EXISTS);
        }
        // 更新到数据库
        bannerMapper.deleteById(bannerId);
    }

}
