package cn.iocoder.mall.admin.sdk.dict;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Sin
 * @time 2019-04-16 20:43
 */
@Data
@Accessors(chain = true)
public class Bean {

    @DictVal(dicKey = "gender", dicValue = "1")
    private String gender;
}
