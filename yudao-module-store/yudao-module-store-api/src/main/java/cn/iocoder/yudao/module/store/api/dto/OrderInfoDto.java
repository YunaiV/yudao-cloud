package cn.iocoder.yudao.module.store.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderInfoDto implements Serializable {

    private static final long uuid = -1;
    private Long id;
    private Long userId;
    private String userName;
    private Long isbn;
    private String bookTitle;
    private String author;
    private Float price;
    private Date purchaseDate;
}
