package cn.iocoder.yudao.module.infra.framework.file.core.client.hbase;

import cn.iocoder.yudao.module.infra.framework.file.core.client.FileClientConfig;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

/**
 * Ftp 文件客户端的配置类
 *
 * @author sur1-123
 */
@Data
public class HbaseFileClientConfig implements FileClientConfig {

    /**
     * 基础路径
     */
    @NotEmpty(message = "基础路径不能为空")
    private String table;
    /**
     * 自定义域名
     */
    @NotEmpty(message = "domain 不能为空")
    @URL(message = "domain 必须是 URL 格式")
    private String domain;
    /**
     * 主机地址
     */
    @NotEmpty(message = "quorum 不能为空")
    private String quorum;

    @NotEmpty(message = "family 不能为空")
    private String family;

    @NotEmpty(message = "图片数据列 不能为空")
    private String column;

    /* 默认分区数 100，用于创建表时设置 */
    private Integer partition = 100;
    /* 默认单分区内可用 Key 长度（排除分区编号），用于创建表时设置 */
    private Integer length = 20;

    /**
     * 存活时长 单位：s, 为满足具体业务需求，可以设置为 null 此时不设置列族存活时间
     * */
    private Integer columnTimeToLive;

}
