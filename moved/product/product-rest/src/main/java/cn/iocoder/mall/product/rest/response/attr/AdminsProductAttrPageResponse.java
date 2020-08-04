package cn.iocoder.mall.product.rest.response.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@ApiModel("商品管理 - 商品规格模块 - 商品规格分页信息 Response")
@Data
@Accessors(chain = true)
public class AdminsProductAttrPageResponse {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 规格名
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 规格值数组
     */
    private List<ProductAttrValue> values;

    @ApiModel("规格值")
    @Data
    @Accessors(chain = true)
    public static class ProductAttrValue {

        /**
         * 规格值编号
         */
        @ApiModelProperty(value = "规格值编号", required = true, example = "1")
        private Integer id;
        /**
         * 规格值名
         */
        @ApiModelProperty(value = "规格值名", required = true, example = "小")
        private String name;
        /**
         * 状态
         */
        @ApiModelProperty(value = "状态", required = true, example = "1")
        private Integer status;
        /**
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
        private Date createTime;
    }


}
