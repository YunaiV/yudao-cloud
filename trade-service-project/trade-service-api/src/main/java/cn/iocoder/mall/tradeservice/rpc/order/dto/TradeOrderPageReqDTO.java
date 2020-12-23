package cn.iocoder.mall.tradeservice.rpc.order.dto;

import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.common.framework.vo.SortingField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 交易订单分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TradeOrderPageReqDTO extends PageParam {

    public static final SortingField ID_ASC = new SortingField("id", "asc");
    public static final SortingField ID_DESC = new SortingField("id", "desc");

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 排序字段数组
     */
    private List<SortingField> sorts;

    /**
     * 额外返回字段
     *
     * @see cn.iocoder.mall.tradeservice.enums.order.TradeOrderDetailFieldEnum
     */
    private Collection<String> fields = Collections.emptySet();

}
