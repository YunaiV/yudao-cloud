package cn.iocoder.mall.payservice.mq.producer.message;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AbstractPayNotifySuccessMessage {

    /**
     * 任务编号
     */
    private Integer id;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 当前通知次数
     */
    private Integer notifyTimes;
    /**
     * 通知地址
     */
    private String notifyUrl;

}
