package cn.iocoder.mall.managementweb.controller.promotion.recommend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("商品推荐明细 VO")
@Data
@Accessors(chain = true)
public class ProductRecommendDetailVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "推荐类型", required = true, example = "1", notes = "参见 ProductRecommendTypeEnum 枚举")
    private Integer type;
    @ApiModelProperty(value = "商品编号", required = true, example = "1")
    private Integer productSpuId;
    @ApiModelProperty(value = "排序", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "参见 CommonStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "备注", required = true, example = "这个活动很牛逼")
    private String memo;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;
    /**
     * 商品信息
     */
    private Spu spu;

    @ApiModel("商品信息")
    @Data
    public static class Spu {

        @ApiModelProperty(value = "SPU 名字", required = true, example = "厮大牛逼")
        private String name;

    }

}
