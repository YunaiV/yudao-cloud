package cn.iocoder.mall.userservice.service.user.bo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 用户分页 BO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserPageBO extends PageParam {

    /**
    * 昵称
     *
     * 模糊
    */
    private String nickname;
    /**
    * 状态
    */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
