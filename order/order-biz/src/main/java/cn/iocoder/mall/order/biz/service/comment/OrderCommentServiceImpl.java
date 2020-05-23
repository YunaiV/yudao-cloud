package cn.iocoder.mall.order.biz.service.comment;

import cn.iocoder.mall.order.biz.convert.comment.OrderCommentConvert;
import cn.iocoder.mall.order.biz.dataobject.comment.OrderCommentDO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentAddDTO;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * OrderCommentServiceImpl
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/17 15:32
 */
@Service
public class OrderCommentServiceImpl implements OrderCommentService {

    private final MongoTemplate mongoTemplate;

    public OrderCommentServiceImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Boolean addOrderComment(
            @Valid OrderCommentAddDTO orderCommentAddDTO) {

        OrderCommentDO orderCommentDO = mongoTemplate
                .save(OrderCommentConvert.INSTANCE.convert(orderCommentAddDTO));
        return null != orderCommentDO ? Boolean.TRUE : Boolean.FALSE;
    }
}
