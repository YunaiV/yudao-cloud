package cn.iocoder.mall.promotion.biz.service.banner;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.promotion.biz.api.enums.PromotionErrorCodeEnum;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListBO;
import cn.iocoder.mall.promotion.biz.bo.banner.BannerListOnReleaseBO;
import cn.iocoder.mall.promotion.biz.convert.BannerConvert;
import cn.iocoder.mall.promotion.biz.dao.BannerMapper;
import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerAddDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerListDTO;
import cn.iocoder.mall.promotion.biz.dto.banner.BannerUpdateDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * banner
 *
 * author: sin
 * time: 2020/5/14 14:19
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerListOnReleaseBO> listBannerOnRelease() {
        PageResult<BannerListBO> pageResult = this.listBanner(
                (BannerListDTO) new BannerListDTO()
                        .setStatus(CommonStatusEnum.ENABLE.getValue())
                        .setTitle(null)
                        .setPageNo(1)
                        .setPageSize(10)
        );
        return BannerConvert.INSTANCE.convertToBO(pageResult.getList());
    }

    @Override
    public PageResult<BannerListBO> listBanner(BannerListDTO dto) {
        IPage<BannerDO> page =  bannerMapper.selectBannerList(dto);
        List<BannerListBO> list = BannerConvert.INSTANCE.convert(page.getRecords());
        return new PageResult<BannerListBO>().setList(list).setTotal(page.getTotal());
    }

    @Override
    public void addBanner(BannerAddDTO adminsBannerAddDTO) {
        // 转换DO
        BannerDO banner = BannerConvert.INSTANCE.convert(adminsBannerAddDTO);
        // 设置默认数据
        banner.setStatus(CommonStatusEnum.ENABLE.getValue());
        banner.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        banner.setCreateTime(new Date());
        // 保存数据
        bannerMapper.insert(banner);
    }

    @Override
    public void updateBanner(BannerUpdateDTO adminsBannerUpdateDTO) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(adminsBannerUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = BannerConvert.INSTANCE.convert(adminsBannerUpdateDTO);
        updateBanner.setUpdateTime(new Date());
        bannerMapper.updateById(updateBanner);
    }

    @Override
    public void updateBannerStatus(Integer adminId, Integer bannerId, Integer status) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerId) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = new BannerDO();
        updateBanner.setId(bannerId);
        updateBanner.setStatus(status);
        updateBanner.setUpdateTime(new Date());
        bannerMapper.updateById(updateBanner);
    }

    @Override
    public void deleteBanner(Integer adminId, Integer bannerId) {
        // 校验 Banner 存在
        if (bannerMapper.selectById(bannerId) == null) {
            throw ServiceExceptionUtil.exception(PromotionErrorCodeEnum.BANNER_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        BannerDO updateBanner = new BannerDO();
        updateBanner.setId(bannerId);
        updateBanner.setUpdateTime(new Date());
        updateBanner.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        bannerMapper.updateById(updateBanner);
    }
}
