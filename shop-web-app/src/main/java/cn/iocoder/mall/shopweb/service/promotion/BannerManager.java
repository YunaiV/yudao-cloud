package cn.iocoder.mall.shopweb.service.promotion;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.banner.BannerRpc;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import cn.iocoder.mall.shopweb.controller.promotion.vo.brand.BannerRespVO;
import cn.iocoder.mall.shopweb.convert.promotion.BannerConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;
import java.util.List;

/**
 * Banner Manager
 */
@Service
@Validated
public class BannerManager {

    @DubboReference(version = "${dubbo.consumer.BannerRpc.version}")
    private BannerRpc bannerRpc;

    public List<BannerRespVO> listBanners() {
        // 获取 Banner 列表
        CommonResult<List<BannerRespDTO>> listBannersResult = bannerRpc.listBanners(
                new BannerListReqDTO().setStatus(CommonStatusEnum.ENABLE.getValue()));
        listBannersResult.checkError();
        // 排序返回
        listBannersResult.getData().sort(Comparator.comparing(BannerRespDTO::getSort));
        return BannerConvert.INSTANCE.convertList(listBannersResult.getData());
    }

}
