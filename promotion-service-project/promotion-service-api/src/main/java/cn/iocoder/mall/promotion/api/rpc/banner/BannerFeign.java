package cn.iocoder.mall.promotion.api.rpc.banner;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient("promotion-service")
public interface BannerFeign {

    @PostMapping("/promotion/banner/createBanner")
    public CommonResult<Integer> createBanner(@RequestBody BannerCreateReqDTO createDTO) ;
    @PostMapping("/promotion/banner/updateBanner")
    public CommonResult<Boolean> updateBanner(@RequestBody BannerUpdateReqDTO updateDTO) ;

    @PostMapping("/promotion/banner/deleteBanner")
    public CommonResult<Boolean> deleteBanner(@RequestBody Integer bannerId) ;

    @PostMapping("/promotion/banner/listBanners")
    public CommonResult<List<BannerRespDTO>> listBanners(@RequestBody BannerListReqDTO listDTO) ;

    @PostMapping("/promotion/banner/pageBanner")
    public CommonResult<PageResult<BannerRespDTO>> pageBanner(@RequestBody BannerPageReqDTO pageDTO);
}
