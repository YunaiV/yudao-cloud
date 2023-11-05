package cn.iocoder.yudao.module.store.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfoDto implements Serializable {

    private static final long uuid = -1;
    private Long id;
    private String name;
    private String password;
    private String role;
    private Boolean valid;
    private Date createTime;
    private Date updateTime;
}
