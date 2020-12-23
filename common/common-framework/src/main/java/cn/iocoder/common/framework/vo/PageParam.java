package cn.iocoder.common.framework.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("分页参数")
public class PageParam implements Serializable {

    @ApiModelProperty(value = "页码，从 1 开始", required = true,example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo;

    @ApiModelProperty(value = "每页条数，最大值为 100", required = true, example = "10")
    @NotNull(message = "每页条数不能为空")
    @Range(min = 1, max = 100, message = "条数范围为 [1, 100]")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public PageParam setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

//    public final int getOffset() {
//        return (pageNo - 1) * pageSize;
//    }

}
