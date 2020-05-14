package cn.iocoder.mall.promotion.biz.bo.banner;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * banner - 已发布的banner
 *
 * author: sin
 * time: 2020/5/14 16:56
 */
@Data
@Accessors(chain = true)
public class BannerListOnReleaseBO implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 图片链接
     */
    private String picUrl;
}
