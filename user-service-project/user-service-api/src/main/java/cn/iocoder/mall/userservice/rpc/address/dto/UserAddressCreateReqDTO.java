package cn.iocoder.mall.userservice.rpc.address.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 用户收件地址创建 Request DTO
*/
@Data
@Accessors(chain = true)
public class UserAddressCreateReqDTO implements Serializable {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 收件人名称
     */
    @NotEmpty(message = "收件人名称不能为空")
    private String name;
    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String mobile;
    /**
     * 地区编码
     */
    @NotNull(message = "地区编码不能为空")
    private Integer areaCode;
    /**
     * 收件详细地址
     */
    @NotEmpty(message = "收件详细地址不能为空")
    private String detailAddress;
    /**
     * 地址类型
     */
    @NotNull(message = "地址类型不能为空")
    private Integer type;

}
