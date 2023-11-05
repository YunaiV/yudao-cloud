package cn.iocoder.yudao.module.store.dal.dataobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@TableName(value = "store_bookinfo", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDO extends BaseDO  {
    @TableId
    private Long id;
    private Long isbn;
    private String title;
    private String author;
    private String press;
    private Date publishDate;
    private Float price;
    private String briefIntro;
    private String imgUrl;
}
