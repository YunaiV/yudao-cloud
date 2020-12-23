package cn.iocoder.mall.managementweb.manager.promotion.brand;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerCreateReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerPageReqVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerRespVO;
import cn.iocoder.mall.managementweb.controller.promotion.brand.vo.BannerUpdateReqVO;
import cn.iocoder.mall.managementweb.convert.promotion.BannerConvert;
import cn.iocoder.mall.promotion.api.rpc.banner.BannerRpc;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
*  Banner  Manager
*/
@Service
public class BannerManager {

    @DubboReference(version = "${dubbo.consumer.BannerRpc.version}")
    private BannerRpc bannerRpc;

    /**
    * 创建 Banner
    *
    * @param createVO 创建 Banner VO
    * @return  Banner
    */
    public Integer createBanner(BannerCreateReqVO createVO) {
        CommonResult<Integer> createBannerResult = bannerRpc.createBanner(BannerConvert.INSTANCE.convert(createVO));
        createBannerResult.checkError();
        return createBannerResult.getData();
    }

    /**
    * 更新 Banner
    *
    * @param updateVO 更新 Banner  VO
    */
    public void updateBanner(BannerUpdateReqVO updateVO) {
        CommonResult<Boolean> updateBannerResult = bannerRpc.updateBanner(BannerConvert.INSTANCE.convert(updateVO));
        updateBannerResult.checkError();
    }

    /**
    * 删除 Banner
    *
    * @param bannerId  Banner 编号
    */
    public void deleteBanner(Integer bannerId) {
        CommonResult<Boolean> deleteBannerResult = bannerRpc.deleteBanner(bannerId);
        deleteBannerResult.checkError();
    }

    /**
    * 获得 Banner 分页
    *
    * @param pageVO  Banner 分页查询
    * @return  Banner 分页结果
    */
    public PageResult<BannerRespVO> pageBanner(BannerPageReqVO pageVO) {
        CommonResult<PageResult<BannerRespDTO>> pageBannerResult = bannerRpc.pageBanner(BannerConvert.INSTANCE.convert(pageVO));
        pageBannerResult.checkError();
        return BannerConvert.INSTANCE.convertPage(pageBannerResult.getData());
    }

}
