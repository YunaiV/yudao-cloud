package cn.iocoder.mall.promotion.api.rpc.banner;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.banner.dto.*;

import java.util.List;

/**
 * Banner Rpc 接口
 */
public interface BannerRpc {

    /**
     * 创建 Banner
     *
     * @param createDTO 创建 Banner DTO
     * @return  Banner 编号
     */
    CommonResult<Integer> createBanner(BannerCreateReqDTO createDTO);

    /**
     * 更新 Banner
     *
     * @param updateDTO 更新 Banner DTO
     */
    CommonResult<Boolean> updateBanner(BannerUpdateReqDTO updateDTO);

    /**
     * 删除 Banner
     *
     * @param bannerId Banner 编号
     */
    CommonResult<Boolean> deleteBanner(Integer bannerId);

    /**
     * 获得 Banner 列表
     *
     * @param listDTO Banner 列表查询 DTO
     * @return  Banner 列表
     */
    CommonResult<List<BannerRespDTO>> listBanners(BannerListReqDTO listDTO);

    /**
     * 获得 Banner 分页
     *
     * @param pageDTO Banner 分页查询
     * @return Banner 分页结果
     */
    CommonResult<PageResult<BannerRespDTO>> pageBanner(BannerPageReqDTO pageDTO);

}
