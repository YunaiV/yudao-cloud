package cn.iocoder.mall.promotion.api.rpc.banner.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Banner Response DTO
 */
@Data
@Accessors(chain = true)
public class BannerRespDTO implements Serializable {

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
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;

}
