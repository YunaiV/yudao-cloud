package cn.iocoder.yudao.module.store.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookInfoDto implements Serializable {

    private static final long uuid = -1;

    private Long isbn;
    private String title;
    private String author;
    private String press;
    private Date publishDate;
    private Float price;
    private String briefIntro;
    private String imgUrl;
}
