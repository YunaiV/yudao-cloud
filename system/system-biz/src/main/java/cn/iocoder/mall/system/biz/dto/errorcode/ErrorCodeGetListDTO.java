package cn.iocoder.mall.system.biz.dto.errorcode;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * todo 考虑是否删除
 * @author ding
 */
@Data
@Accessors(chain = true)
public class ErrorCodeGetListDTO {
    /**
     * 错误码编号数组
     *
     * 如果传入空，则不进行错误码编号的过滤
     */
    private Collection<Integer> codes;
}
