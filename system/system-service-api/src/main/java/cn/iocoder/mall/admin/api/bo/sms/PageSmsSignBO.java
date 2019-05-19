package cn.iocoder.mall.admin.api.bo.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * sms page
 *
 * @author Sin
 * @time 2019/5/19 4:23 PM
 */
@Data
@Accessors(chain = true)
public class PageSmsSignBO {

    private Integer count;

    private Long current;

    private Long size;

    private Long total;

    private List<Sign> data;

    @Data
    @Accessors(chain = true)
    public class Sign {
        /**
         * 编号
         */
        private Integer id;
        /**
         * 签名id 这个是第三方的
         */
        private String platformId;
        /**
         * 签名名称
         */
        private String sign;
        /**
         * 审核状态
         *
         * - 1、审核中
         * - 2、审核成功
         * - 3、审核失败
         */
        private Integer applyStatus;
        /**
         * 审核信息
         */
        private String applyMessage;
    }
}
